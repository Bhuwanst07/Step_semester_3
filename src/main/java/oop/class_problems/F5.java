package oop.class_problems;

public class F5 {

    interface Reservable {
        void reserve(String memberId);
    }

    interface Downloadable {
        void download();
    }

    static abstract class Resource {

        String id;

        Resource(String id) {
            this.id = id;
        }
    }

    static class Book extends Resource
            implements Reservable {

        Book(String id) {
            super(id);
        }

        public void reserve(String memberId) {
            System.out.println(
                    id + " reserved for " + memberId
            );
        }
    }

    static class EBook extends Resource
            implements Downloadable {

        EBook(String id) {
            super(id);
        }

        public void download() {
            System.out.println(id + " downloaded");
        }
    }

    static class Library {

        private Resource[] resources =
                new Resource[100];

        private int size = 0;

        void add(Resource resource) {

            if (findIndex(resource.id) != -1) {
                System.out.println("duplicate rejected");
                return;
            }

            if (size == resources.length) {
                System.out.println("Library is full");
                return;
            }

            int position = size;

            while (position > 0 &&
                    resources[position - 1].id.compareTo(
                            resource.id
                    ) > 0) {

                resources[position] =
                        resources[position - 1];

                position--;
            }

            resources[position] = resource;
            size++;

            System.out.println(resource.id + " added");
        }

        int findIndex(String id) {

            int left = 0;
            int right = size - 1;

            while (left <= right) {

                int mid = left + (right - left) / 2;

                int comparison =
                        resources[mid].id.compareTo(id);

                if (comparison == 0) {
                    return mid;
                }

                if (comparison < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return -1;
        }

        void reserve(String id, String memberId) {

            int index = findIndex(id);

            if (index == -1) {
                System.out.println(id + " not found");
                return;
            }

            Resource resource = resources[index];

            if (resource instanceof Reservable) {
                ((Reservable) resource).reserve(memberId);
            } else {
                System.out.println(
                        id + " rejected: reservation unsupported"
                );
            }
        }

        void download(String id) {

            int index = findIndex(id);

            if (index == -1) {
                System.out.println(id + " not found");
                return;
            }

            Resource resource = resources[index];

            if (resource instanceof Downloadable) {
                ((Downloadable) resource).download();
            } else {
                System.out.println(
                        id + " rejected: download unsupported"
                );
            }
        }

        void find(String id) {

            int index = findIndex(id);

            if (index == -1) {
                System.out.println(id + " not found");
            } else {
                System.out.println(
                        id + " found at index " + index
                );
            }
        }
    }

    public static void main(String[] args) {

        Library library = new Library();

        library.add(new Book("B1"));
        library.add(new Book("B1"));
        library.add(new EBook("E1"));

        library.reserve("B1", "M1");

        library.download("B1");
        library.download("E1");

        library.find("E1");
    }
}