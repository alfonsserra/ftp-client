package com.systelab;

import com.jcraft.jsch.*;

import java.io.InputStream;

public class SFTP {

    public static final int CONNECT_TIMEOUT = 10000;


    public static ChannelSftp connect(String username, String password, String host, int port) {
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            JSch jsch = new JSch();
            Session session = jsch.getSession(username, host, port);
            session.setConfig(config);
            session.setConfig("PreferredAuthentications", "password");
            session.setPassword(password);
            session.connect(CONNECT_TIMEOUT);

            Channel channel = session.openChannel("sftp");
            ChannelSftp sftp = (ChannelSftp) channel;
            sftp.connect(CONNECT_TIMEOUT);
            return sftp;
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void disconnect(ChannelSftp sftp) {
        sftp.disconnect();
    }

    public static void changeDirectory(ChannelSftp sftp, String dirName) {
        try {
            sftp.mkdir(dirName);
            sftp.cd(dirName);
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }


    public static void sendFile(ChannelSftp sftp, InputStream is, String remoteFilename) throws SftpException {
        sftp.put(is, remoteFilename, ChannelSftp.OVERWRITE);
    }

}
