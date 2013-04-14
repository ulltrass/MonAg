/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.networkmonitoragent.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ovi
 */
public class FileUtil {

    public static List<File> getFilesFromPathThatStartWith(String path, final String endsWith) {
        List<File> fileList = new ArrayList<File>();

        File directory = new File(path);
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File directory, String fileName) {
                return fileName.endsWith(endsWith);
            }
        };

        File[] files = directory.listFiles(filter);
        for (File file : files) {
            fileList.add(file);
        }

        return fileList;

    }

    public static void cleanCSVFiles(String path) {
        File directory = new File(path);
        FilenameFilter csvFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".csv");
            }
        };

        String[] files = directory.list(csvFilter);

        for (String fileName : files) {
            new File(fileName).delete();
        }
    }

    public static void saveContactsFile(List<String> contactsList) {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File("./settings/contacts.csv");
            File directory = new File("./settings");

            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }
            bufferedWriter = new BufferedWriter(new FileWriter(file));

            for (String contact : contactsList) {
                bufferedWriter.write(contact);
                bufferedWriter.newLine();
            }

        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static List<String> readContactsFile() {
        List<String> contacts = new ArrayList<String>();
        File file = new File("./settings/contacts.csv");
        if (file.exists()) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                String contact = null;

                while ((contact = bufferedReader.readLine()) != null) {
                    if (!contact.equals("") && contact.split(",").length == 3) {
                        contacts.add(contact);
                    }
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }


        return contacts;
    }

    public static void saveContentToFile(String path, String fileName, List<String> contentList) {
        File pathFile = new File(path);
        File contentFile = new File(fileName);
        BufferedWriter bufferedWriter = null;

        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(path + "/" + contentFile));

            for (String contentLine : contentList) {
                bufferedWriter.write(contentLine);
                bufferedWriter.newLine();
            }

        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

    public static List<String> readContentFromFileAsList(String path, String fileName) {
        File propertiesFile = new File(path + "/" + fileName);
        List<String> contentList = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        String line = null;

        try {
            if (propertiesFile.exists()) {
                bufferedReader = new BufferedReader(new FileReader(propertiesFile.getAbsolutePath()));
                while ((line = bufferedReader.readLine()) != null) {
                    contentList.add(line);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return contentList;
    }
    
     public static void deleteConfigFile(String path, String fileName) {
        File propertiesFile = new File(path + "/" + fileName);
        propertiesFile.delete();
    }
}
