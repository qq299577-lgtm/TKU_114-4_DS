interface MessageSender {
    void send(String receiver, String message);
}

class EmailSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        if (receiver == null || receiver.trim().isEmpty() || message == null || message.trim().isEmpty()) {
            System.out.println("[Email 錯誤] 接收者或訊息不可為空");
            return;
        }
        System.out.printf("[Email 發送] 至 %s: %s\n", receiver, message);
    }
}

class SmsSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        if (receiver == null || receiver.trim().isEmpty() || message == null || message.trim().isEmpty()) {
            System.out.println("[SMS 錯誤] 接收者或訊息不可為空");
            return;
        }
        System.out.printf("[SMS 簡訊] 至 %s: %s\n", receiver, message);
    }
}

class ConsoleSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        if (receiver == null || receiver.trim().isEmpty() || message == null || message.trim().isEmpty()) {
            System.out.println("[Console 錯誤] 接收者或訊息不可為空");
            return;
        }
        System.out.printf("[控制台輸出] %s -> %s\n", receiver, message);
    }
}

public class MessageSenderSystem {
    public static void notify(MessageSender sender, String receiver, String message) {
        sender.send(receiver, message);
    }

    public static void main(String[] args) {
        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender console = new ConsoleSender();

        notify(email, "user@example.com", "您的驗證碼為 1234");
        notify(sms, "0912345678", "訂單已出貨");
        notify(console, "Admin", "伺服器重啟完成");
        notify(email, "", "空白測試");
    }
}