package com.aszz.cert.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface CertService {
    String downloadFilesTest(List<String> filePaths, HttpServletRequest request, HttpServletResponse res) throws IOException;
}
