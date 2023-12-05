package br.com.qrcodegen.qrcodegen;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.zxing.common.StringUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
public class QrCodeGenApplication {


    public static void main(String[] args) {
        SpringApplication.run(QrCodeGenApplication.class, args);
    }

}
