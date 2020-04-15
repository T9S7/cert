package com.aszz.cert.admin.kafka;


import com.aszz.cert.admin.dto.CertInfoDto;
import com.aszz.cert.mapper.CertInfoMapper;
import com.aszz.cert.mapper.CertificateMapper;
import com.aszz.cert.model.CertInfo;
import com.aszz.cert.model.CertInfoExample;
import com.aszz.cert.model.Certificate;
import com.aszz.cert.model.CertificateExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 监听kafka信息
 */
@Component
@Slf4j
public class KafkaReceiver {

    @Autowired
    private CertificateMapper certificateMapper;

    @Autowired
    private CertInfoMapper certInfoMapper;

    private Map<String, List<CertInfoDto>> map = new HashMap<>();

    private List<CertInfoDto> certInfoDtoList = new ArrayList<>();

    @KafkaListener(id = "mqtt-log", topics = {"kafka-test"}, containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<?, ?> record) throws InterruptedException {
        String message = record.value().toString();
        log.info("----message =" + record.value());
        handleMessage(message);
    }

    public void handleMessage(String message) {

        CertInfoDto newCertInfo = new CertInfoDto();
//        String[] msgArray =message.split("\n");

//        for(int i =0;i<msgArray.length;i++){
//            String message= msgArray[i];
        String[] messageArray = message.split(":");
        message = timeStamp2Date(messageArray[0]) + messageArray[1] + "\n";
        String mqttClientID = "";

        int separatorIndex = message.indexOf("|");
        if (separatorIndex > -1) {
            mqttClientID = message.substring(separatorIndex + 1, separatorIndex + 13);
            log.info("----mqttClientID =" + mqttClientID);
            //根据mqttClientID查询是否有记录
            CertificateExample certificateExample = new CertificateExample();
            certificateExample.createCriteria().andCertMqttClientIdEqualTo(mqttClientID);
            List<Certificate> certificateList = certificateMapper.selectByExample(certificateExample);
            if (certificateList == null || certificateList.size() == 0) {
                return;
            }

            Long certId = certificateList.get(0).getId();

//                newCertInfo.setMqttClientID(mqttClientID);
//                newCertInfo.setCertId(certId);
            List<CertInfoDto> savedCertInfoDtoList = map.get(mqttClientID);
            if (message.contains("New client connected")) {
                newCertInfo.setCertOnlineTime(0L);
                newCertInfo.setCertConnectNum(1L);
                newCertInfo.setStartTime(Long.valueOf(messageArray[0]));
                newCertInfo.setCertLog(message);

                certInfoDtoList.add(newCertInfo);
                map.put(mqttClientID, certInfoDtoList);
            } else if (message.contains("disconnecting")) {
                newCertInfo.setStartTime(savedCertInfoDtoList.get(savedCertInfoDtoList.size() - 1).getStartTime());
                newCertInfo.setEndTime(Long.valueOf(messageArray[0]));
                newCertInfo.setCertConnectNum(0L);
                newCertInfo.setCertOnlineTime(Long.valueOf(messageArray[0]) - savedCertInfoDtoList.get(savedCertInfoDtoList.size() - 1).getStartTime());
                newCertInfo.setCertLog(message);

                certInfoDtoList.add(newCertInfo);
                map.put(mqttClientID, certInfoDtoList);
            } else {
                newCertInfo.setStartTime(savedCertInfoDtoList.get(savedCertInfoDtoList.size() - 1).getStartTime());
                newCertInfo.setCertConnectNum(0L);
                newCertInfo.setCertOnlineTime(0L);
                newCertInfo.setCertLog(message);
                certInfoDtoList.add(newCertInfo);
                map.put(mqttClientID, certInfoDtoList);
            }

            //每10条，保存到数据库
            int n = 0;
            List<CertInfoDto> batchRecordList = new ArrayList<>();
            if (certInfoDtoList.size() % 10 == 0) {
                n = certInfoDtoList.size() / 10;
                for (int i = (n - 1) * 10; i < n * 10; i++) {
                    batchRecordList.add(certInfoDtoList.get(i));
                }
                insertOrUpdateCertInfo(batchRecordList, certId);
//                certInfoDtoList.clear();
            }
        }

    }

    public String timeStamp2Date(String seconds) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    public void insertOrUpdateCertInfo(List<CertInfoDto> certInfoDtoList, Long certId) {
        Long onLineTime = 0L;
        Long connectionNum = 0L;
        String tlsLog = "";
        for (CertInfoDto certInfoDto : certInfoDtoList) {
            onLineTime = onLineTime + certInfoDto.getCertOnlineTime();
            connectionNum = connectionNum + certInfoDto.getCertConnectNum();
            tlsLog = tlsLog + certInfoDto.getCertLog();
        }

        CertInfoExample certInfoExample = new CertInfoExample();
        certInfoExample.createCriteria().andCertIdEqualTo(certId);
        List<CertInfo> certInfoList = certInfoMapper.selectByExampleWithBLOBs(certInfoExample);
        if (certInfoList == null || certInfoList.size() == 0) {
            //插入
            CertInfo certInfo = new CertInfo();
            certInfo.setCertId(certId);
            certInfo.setCertLog(tlsLog);
            certInfo.setCertConnectNum(connectionNum);
            certInfo.setCertOnlineTime(onLineTime);

            certInfo.setCreateTime(new Date());
            certInfo.setCreateUserId(1L);
            certInfo.setUpdateTime(new Date());
            certInfo.setUpdateUserId(1L);
            certInfoMapper.insert(certInfo);
        } else {
            //更新
            CertInfo certInfo = certInfoList.get(0);
            certInfo.setCertLog(tlsLog + certInfo.getCertLog());
            certInfo.setUpdateTime(new Date());
            //连接时长是叠加的
            certInfo.setCertOnlineTime(onLineTime + certInfo.getCertOnlineTime());
            //连接次数
            certInfo.setCertConnectNum(connectionNum + certInfo.getCertConnectNum());
            certInfoMapper.updateByPrimaryKeySelective(certInfo);
        }
    }

    public static String generateMqttClientID(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(10));
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10));
        }
        return new String(digits);
    }

    public static void main(String[] args) {
        System.out.println(generateMqttClientID(12) + "##########");
        System.out.println(new Random().nextInt(10));
    }

}
