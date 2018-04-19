package com.systelab;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class RemoteSFTP {

    public void connectAndSendFiles(String username, String password, String host, int port) {
        ChannelSftp sftp = null;
        try {
            sftp = SFTP.connect(username, password, host, port);
            if (sftp != null) {
                SFTP.changeDirectory(sftp, "temp");
                sendJSONFile(sftp, "sample.json");
                sendImageFile(sftp, "sample.jpg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sftp != null)
                SFTP.disconnect(sftp);
        }
        System.exit(0);

    }

    private void sendImageFile(ChannelSftp sftp, String filename) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            SFTP.sendFile(sftp, fis, filename);
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    public void sendJSONFile(ChannelSftp sftp, String filename) throws IOException {
        InputStream is = null;
        try {
            String content = "{\"pattern\": \"" + "homogenous, speckled, centromere, nucleolar" + "\"}";
            is = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
            SFTP.sendFile(sftp, is, filename);
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length>4)
        new RemoteSFTP().connectAndSendFiles("username", "password", "127.0.0.1", 22);
    }
}
