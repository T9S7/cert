package com.aszz.cert.admin.quartz;

import com.aszz.cert.admin.bo.SpringContextUtils;
import com.aszz.cert.mapper.CertAlarmMapper;
import com.aszz.cert.mapper.CertificateMapper;
import com.aszz.cert.model.CertAlarm;
import com.aszz.cert.model.CertAlarmExample;
import com.aszz.cert.model.Certificate;
import com.aszz.cert.model.CertificateExample;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.List;


public class CertAlarmInfoTask implements Job {

    private CertificateMapper certificateMapper = (CertificateMapper) SpringContextUtils.getBeanByClass(CertificateMapper.class);

    private CertAlarmMapper certAlarmMapper = (CertAlarmMapper) SpringContextUtils.getBeanByClass(CertAlarmMapper.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        Long userId = jobDataMap.getLong("userId");
        CertificateExample certificateExample = new CertificateExample();
        certificateExample.createCriteria().andCreateUserIdEqualTo(userId);
        //获取用户拥有的所有设备证书
        List<Certificate> certificateList = certificateMapper.selectByExample(certificateExample);

        for (Certificate certificate : certificateList) {
            Date expDate = certificate.getCertExpTime();
            Date nowDate = new Date();
            if (nowDate.getTime() > expDate.getTime()) {
                //已过期
                CertAlarm certAlarm = new CertAlarm();
                certAlarm.setCertAlarmInfo("警告：该证书已过期！");
                certAlarm.setCertId(certificate.getId());
                certAlarm.setCreateTime(nowDate);
                certAlarm.setUpdateTime(nowDate);
                certAlarm.setCreateUserId(1L);
                certAlarm.setUpdateUserId(1L);
                CertAlarmExample certAlarmExample = new CertAlarmExample();
                //告警类型的日志信息
                certAlarmExample.createCriteria().andCertIdEqualTo(certificate.getId()).andCertAlarmTypeEqualTo(0);
                List<CertAlarm> certAlarmList = certAlarmMapper.selectByExample(certAlarmExample);
                if (certAlarmList == null || certAlarmList.size() == 0) {
                    certAlarmMapper.insert(certAlarm);
                } else {
                    certAlarm.setId(certAlarmList.get(0).getId());
                    certAlarm.setUpdateTime(new Date());
                    certAlarmMapper.updateByPrimaryKeySelective(certAlarm);
                }
            }

        }
    }
}
