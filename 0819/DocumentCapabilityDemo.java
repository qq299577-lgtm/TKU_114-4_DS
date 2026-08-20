interface Exportable {
    void exportFile(String path);
}

interface Compressible {
    void compress(String algorithm);
}

class BackupDocument implements Exportable, Compressible {
    private String docName;

    public BackupDocument(String docName) {
        this.docName = docName;
    }

    @Override
    public void exportFile(String path) {
        System.out.printf("文件 [%s] 已匯出至路徑: %s\n", docName, path);
    }

    @Override
    public void compress(String algorithm) {
        System.out.printf("文件 [%s] 使用 %s 演算法壓縮完成\n", docName, algorithm);
    }
}

public class DocumentCapabilityDemo {
    public static void main(String[] args) {
        BackupDocument doc = new BackupDocument("2026_財務報表.pdf");

        Exportable exportRef = doc;
        Compressible compressRef = doc;

        exportRef.exportFile("/backup/reports/");
        compressRef.compress("GZIP");

        System.out.println("兩個介面引用指向同一物件: " + (exportRef == compressRef));
    }
}