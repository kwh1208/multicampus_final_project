package eat_schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private static final String ADMIN_ADDRESS = "kwh1208@naver.com";

    public void sendEmail() throws Exception {
        // 메일 관련 정보
        String host = "smtp.naver.com";
        String username = "SMTP 허용 이메일 계정";
        String password = "비밀번호";
        int port = 465;

        // 메일 내용
        String subject = "메일 발송시 제목";
        String content = "메일 발송시 내용";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.ssl.enable", true);
        props.put("mail.smtp.ssl.trust", host);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            String user = username;
            String pass = password;
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        session.setDebug(true);

        // 보내는 사람
        String frm = "kwh1208";

        // 받는 사람
        String to = "받는 사람 이메일";

        try {
            // 메일 내용 넣을 객체와, 이를 도와주는 Helper 객체 생성
            MimeMessage mail = new MimeMessage(session);

            mail.setFrom(new InternetAddress(frm)); // 보내는 사람 세팅
            mail.setRecipient(Message.RecipientType.TO, new InternetAddress(to)); // 받는 사람 세팅
            mail.setSubject(subject);	// 제목 세팅
            mail.setText(content);	// 내용 세팅

            // 메일 전송
            mailSender.send(mail);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}


