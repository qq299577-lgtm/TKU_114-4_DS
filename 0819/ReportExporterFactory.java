interface ReportExporter {
    void export(String title, int[] values);
}

class CsvExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("--- CSV 輸出 ---");
        System.out.println("Title," + (title == null ? "Untitled" : title));
        System.out.print("Values");
        if (values != null) {
            for (int v : values) {
                System.out.print("," + v);
            }
        }
        System.out.println("\n");
    }
}

class JsonExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("--- JSON 輸出 ---");
        System.out.println("{");
        System.out.printf("  \"title\": \"%s\",\n", (title == null ? "Untitled" : title));
        System.out.print("  \"values\": [");
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i] + (i < values.length - 1 ? ", " : ""));
            }
        }
        System.out.println("]\n}\n");
    }
}

class TextExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("--- 純文字輸出 ---");
        System.out.println("標題: " + (title == null ? "Untitled" : title));
        System.out.print("數據內容: ");
        if (values == null || values.length == 0) {
            System.out.println("無資料");
        } else {
            for (int v : values) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

public class ReportExporterFactory {
    public static ReportExporter createExporter(String format) {
        if (format == null) {
            return new TextExporter();
        }
        switch (format.trim().toUpperCase()) {
            case "CSV":
                return new CsvExporter();
            case "JSON":
                return new JsonExporter();
            default:
                return new TextExporter();
        }
    }

    public static void exportReport(ReportExporter exporter, String title, int[] values) {
        if (exporter != null) {
            exporter.export(title, values);
        }
    }

    public static void main(String[] args) {
        int[] data = {10, 20, 30, 40, 50};

        ReportExporter csv = createExporter("CSV");
        ReportExporter json = createExporter("JSON");
        ReportExporter txt = createExporter("TXT");
        ReportExporter unknown = createExporter("XML");

        exportReport(csv, "月營收統計", data);
        exportReport(json, "伺服器負載紀錄", data);
        exportReport(txt, "簡單摘要", data);
        exportReport(unknown, "未支援格式測試", data);
        exportReport(txt, "Null 數值測試", null);
    }
}