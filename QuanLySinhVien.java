import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class SinhVien {
    private String id;
    private String name;
    private double marks;

    public SinhVien(String id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getRank() {
        if (marks < 5.0) return "Fail";
        if (marks < 6.5) return "Medium";
        if (marks < 7.5) return "Good";
        if (marks < 9.0) return "Very Good";
        return "Excellent";
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Marks: %.2f, Rank: %s", id, name, marks, getRank());
    }
}

public class QuanLySinhVien {
    private ArrayList<SinhVien> danhSachSV = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Thêm sinh viên
    public void themSinhVien() {
        System.out.print("Enter ID: ");
    String id = scanner.nextLine();

    String name = "";
    boolean validName = false;
    
    while (!validName) {
        System.out.print("Enter Name: ");
        name = scanner.nextLine();
        
        // Check if the input is a number
        if (isNumeric(name)) {
            System.out.println("Error: Name cannot be a number. Please enter a valid name.");
        } else {
            validName = true;
        }
    }

    System.out.print("Enter Score: ");
    double marks = scanner.nextDouble();
    scanner.nextLine(); // Consume newline
    
    danhSachSV.add(new SinhVien(id, name, marks));
    System.out.println("Student has been added");
}

// Helper method to check if a string is numeric
private boolean isNumeric(String str) {
    if (str == null) {
        return false;
    }
    try {
        Double.parseDouble(str); // Try to parse the string as a double
        return true; // If successful, it's numeric
    } catch (NumberFormatException e) {
        return false; // If an exception occurs, it's not numeric
    }
}
    // Hiển thị danh sách sinh viên
    public void hienThiSinhVien() {
        if (danhSachSV.isEmpty()) {
            System.out.println("List student empty");
            return;
        }
        System.out.println("List student:");
        for (SinhVien sv : danhSachSV) {
            System.out.println(sv);
        }
    }

    // Tìm kiếm sinh viên theo ID
    public void timKiemSinhVien() {
        System.out.print("Type ID you want to find ");
        String id = scanner.nextLine();
        for (SinhVien sv : danhSachSV) {
            if (sv.getId().equalsIgnoreCase(id)) {
                System.out.println("Student ID: " + sv);
                return;
            }
        }
        System.out.println("Cant find student .");
    }

    // Sửa thông tin sinh viên
    public void suaSinhVien() {
        System.out.print("Type ID Student you need edit: ");
        String id = scanner.nextLine();
        for (SinhVien sv : danhSachSV) {
            if (sv.getId().equalsIgnoreCase(id)) {
                System.out.print("New name: ");
                String newName = scanner.nextLine();
                System.out.print("New score: ");
                double newMarks = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                sv.setName(newName);
                sv.setMarks(newMarks);
                System.out.println("Student has been edited.");
                return;
            }
        }
        System.out.println("Cant find student.");
    }

    // Xóa sinh viên
    public void xoaSinhVien() {
        System.out.print("Type ID you need to remove: ");
        String id = scanner.nextLine();
        danhSachSV.removeIf(sv -> sv.getId().equalsIgnoreCase(id));
        System.out.println("Student has been remove.");
    }

    // Sắp xếp sinh viên bằng Quick Sort
    public void sapXepBangQuickSort() {
        quickSort(0, danhSachSV.size() - 1);
        System.out.println("List has been updated.");
    }

    // Hàm đệ quy Quick Sort
    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);

            // Đệ quy sắp xếp hai phần
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    // Hàm phân đoạn (Partition)
    private int partition(int low, int high) {
        double pivot = danhSachSV.get(high).getMarks();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (danhSachSV.get(j).getMarks() > pivot) {
                i++;
                // Hoán đổi hai phần tử
                Collections.swap(danhSachSV, i, j);
            }
        }

        // Đặt phần tử chốt vào đúng vị trí
        Collections.swap(danhSachSV, i + 1, high);
        return (i + 1);
    }

    // Chương trình chính
    public static void main(String[] args) {
        QuanLySinhVien qlsv = new QuanLySinhVien();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Student Manager ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Student List");
            System.out.println("3. Search Student");
            System.out.println("4. Edit Student");
            System.out.println("5. Remove Student");
            System.out.println("6. Sort Student");
            System.out.println("7. Exit");
            System.out.print("Choose:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> qlsv.themSinhVien();
                case 2 -> qlsv.hienThiSinhVien();
                case 3 -> qlsv.timKiemSinhVien();
                case 4 -> qlsv.suaSinhVien();
                case 5 -> qlsv.xoaSinhVien();
                case 6 -> qlsv.sapXepBangQuickSort();
                case 7 -> {
                    System.out.println("Exit the program");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid Selection");
            }
        }
    }
}
