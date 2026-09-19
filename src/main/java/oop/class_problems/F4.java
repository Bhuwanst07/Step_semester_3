package oop.class_problems;

public class F4 {

    public interface Playable {

        String play();

        String play(int fromSecond);

        String pause();
    }

    public static abstract class MediaFile {

        private static int fileCounter = 1000;

        private final String fileId;

        public MediaFile() {
            fileCounter++;

            fileId =
                    "MF-" + fileCounter;
        }

        public abstract String getFormatInfo();

        public String getFileId() {
            return fileId;
        }
    }

    public static class AudioFile
            extends MediaFile
            implements Playable {

        private final String title;

        public AudioFile(String title) {
            this.title = title;
        }

        @Override
        public String play() {
            return "Playing audio: "
                    + title;
        }

        @Override
        public String play(int fromSecond) {

            int minutes =
                    fromSecond / 60;

            int seconds =
                    fromSecond % 60;

            return "Playing audio: "
                    + title
                    + " from "
                    + minutes
                    + ":"
                    + String.format(
                            "%02d",
                            seconds
                    );
        }

        @Override
        public String pause() {
            return "Paused audio: "
                    + title;
        }

        @Override
        public String getFormatInfo() {
            return "Audio file, ID: "
                    + getFileId();
        }
    }

    public static class Podcast
            implements Playable {

        private final String showName;
        private final int episodeNumber;

        public Podcast(
                String showName,
                int episodeNumber) {

            if (episodeNumber <= 0) {
                throw new IllegalArgumentException(
                        "Episode number must be positive"
                );
            }

            this.showName = showName;
            this.episodeNumber = episodeNumber;
        }

        @Override
        public String play() {
            return "Streaming episode "
                    + episodeNumber
                    + " of "
                    + showName;
        }

        @Override
        public String play(int fromSecond) {

            return "Streaming episode "
                    + episodeNumber
                    + " of "
                    + showName
                    + " from "
                    + fromSecond
                    + " seconds";
        }

        @Override
        public String pause() {
            return "Podcast paused: "
                    + showName;
        }
    }

    public static void launchAll(
            Playable[] items) {

        for (Playable item : items) {
            System.out.println(
                    item.play()
            );
        }
    }

    public static void main(String[] args) {

        AudioFile a =
                new AudioFile(
                        "Morning Jazz"
                );

        System.out.println(
                a.play()
        );

        System.out.println(
                a.play(30)
        );

        System.out.println(
                a.getFormatInfo()
        );

        Podcast p =
                new Podcast(
                        "Tech Talk",
                        12
                );

        System.out.println(
                p.play()
        );

        // Upcasting:
        // AudioFile stored as Playable.
        Playable ref = a;

        System.out.println(
                ref.play()
        );

        Playable[] items = {
            ref,
            p
        };

        launchAll(items);
    }
}