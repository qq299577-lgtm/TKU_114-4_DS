interface Playable {
    void play();
}

interface MediaCompressible {
    void compress();
}

abstract class MediaFile {
    private String fileName;
    private double fileSizeMB;

    public MediaFile(String fileName, double fileSizeMB) {
        this.fileName = fileName;
        this.fileSizeMB = Math.max(fileSizeMB, 0);
    }

    public String getFileName() { return fileName; }
    public double getFileSizeMB() { return fileSizeMB; }

    public abstract void displayInfo();
}

class ImageFile extends MediaFile implements MediaCompressible {
    public ImageFile(String fileName, double fileSizeMB) {
        super(fileName, fileSizeMB);
    }

    @Override
    public void displayInfo() {
        System.out.printf("[圖片] 檔案: %s, 大小: %.1f MB\n", getFileName(), getFileSizeMB());
    }

    @Override
    public void compress() {
        System.out.println(" -> 正在使用 JPEG 演算法壓縮圖片...");
    }
}

class AudioFile extends MediaFile implements Playable, MediaCompressible {
    public AudioFile(String fileName, double fileSizeMB) {
        super(fileName, fileSizeMB);
    }

    @Override
    public void displayInfo() {
        System.out.printf("[音訊] 檔案: %s, 大小: %.1f MB\n", getFileName(), getFileSizeMB());
    }

    @Override
    public void play() {
        System.out.println(" -> 正在解碼並播放音訊串流...");
    }

    @Override
    public void compress() {
        System.out.println(" -> 正在使用 MP3 演算法壓縮音訊...");
    }
}

class VideoFile extends MediaFile implements Playable, MediaCompressible {
    public VideoFile(String fileName, double fileSizeMB) {
        super(fileName, fileSizeMB);
    }

    @Override
    public void displayInfo() {
        System.out.printf("[視訊] 檔案: %s, 大小: %.1f MB\n", getFileName(), getFileSizeMB());
    }

    @Override
    public void play() {
        System.out.println(" -> 正在使用 H.264 解碼並渲染視訊畫面...");
    }

    @Override
    public void compress() {
        System.out.println(" -> 正在使用 H.265/HEVC 進行視訊轉碼壓縮...");
    }
}

public class MediaProcessingSystem {
    public static void main(String[] args) {
        MediaFile[] playlist = {
            new ImageFile("avatar.png", 3.5),
            new AudioFile("song.flac", 45.0),
            new VideoFile("lecture.mp4", 650.0)
        };

        for (MediaFile media : playlist) {
            media.displayInfo();
            if (media instanceof Playable playable) {
                playable.play();
            }
            if (media instanceof MediaCompressible compressible) {
                compressible.compress();
            }
            System.out.println();
        }
    }
}