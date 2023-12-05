package br.com.qrcodegen.qrcodegen;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.zxing.common.StringUtils;

@SpringBootApplication
public class QrCodeGenApplication implements CommandLineRunner {

    @Autowired
    private QrCodeGenService qrCodeGenService;

    public static void main(String[] args) {
        SpringApplication.run(QrCodeGenApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o texto para gerar o QR Code: ");
            String text = scanner.nextLine();

            System.out.println("Texto digitado: " + text);
            if (StringUtils.isNotBlank(text)) {
                qrCodeGenService.genQrCode(text);
            }
        }
    }

}
