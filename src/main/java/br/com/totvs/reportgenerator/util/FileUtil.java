package br.com.totvs.reportgenerator.util;

//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;


public class FileUtil {
//
//    private static final String  rootDirPath = "src/main/resources/static/files/";
//
//    public void saveFile(MultipartFile file){
//        try{
//            save(rootDirPath, file);
//        } catch (Exception e){
//            System.out.println(e);
//        }
//    }
//
//    private void save(String dir, MultipartFile file) {
//        try {
//            File dirPath = new File(rootDirPath);
//            deleteFiles(dirPath);
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(rootDirPath+file.getOriginalFilename());
//            Files.write(path, bytes);
//
//        } catch (IOException e) {
//            throw new RuntimeException("Erro ao salvar o arquivo");
//        }
//    }
//
//    private void deleteFiles(File file){
//        File[] files = file.listFiles();
//        for(File f : files){
//            f.delete();
//        }
//    }
}
