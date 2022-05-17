package tool;

import java.util.Scanner;

/**
 *
 * @author tri
 */
public class Tool {

    private static Scanner sc = new Scanner(System.in);

    //Nhập vào một số nguyên kiển int
    public static int getAnInteger(String inforMessage, String errorMessage) {
        int n;
        while (true) {
            try {
                System.out.printf(inforMessage);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }

    //Hàm lấy một số nguyên nằm trong khoảng nhất định
    public static int getAnInteger(String inforMessage, String errorMessage, int lowerBound, int upperBound) {
        //đổi chỗ upper và lower nếu upper < lower
        int n;
        if (upperBound < lowerBound) {
            int tmp = upperBound;
            upperBound = lowerBound;
            lowerBound = tmp;
        }

        while (true) {
            try {
                System.out.printf(inforMessage);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                } else {
                    return n;
                }
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }

    }

    //Nhập vào một số nguyên kiểu long
    public static long getALong(String inforMessage, String errorMessage) {
        long n;
        while (true) {
            try {
                System.out.printf(inforMessage);
                n = Long.parseLong(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }

    public static long getALong(String inforMessage, String errorMessage, long lowerBound, long upperBound) {
        long n;
        if (lowerBound > upperBound) {
            long tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }

        while (true) {
            try {
                System.out.printf(inforMessage);
                n = Long.parseLong(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                } else {
                    return n;
                }
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }

    public static double getADouble(String inforMessage, String errorMessage) {
        double n;
        while (true) {
            try {
                System.out.printf(inforMessage);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }

    public static double getADouble(String inforMessage, String errorMessage, double lowerBound, double upperBound) {
        double n;
        if (lowerBound > upperBound) {
            double tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }

        while (true) {
            try {
                System.out.printf(inforMessage);
                n = Double.parseDouble(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                } else {
                    return n;
                }
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }

    //Nhập vào một chuỗi String khác rỗng
    public static String getString(String informessage, String errorMessage) {
        String str;
        while (true) {
            System.out.print(informessage);
            str = sc.nextLine();
            if (str.trim().isEmpty()) {
                System.out.println(errorMessage);
            } else {
                return str;
            }
        }
    }

    //Nhập vào một chuỗi kí tự theo định dạng
    public static String getID(String inforMessage, String errorMessage, String format) {
        String id;
        boolean match;
        while (true) {
            System.out.print(inforMessage);
            id = sc.nextLine().trim().toUpperCase();
            match = id.matches(format);
            if (id.length() == 0 || id.isEmpty() || match == false) {
                System.out.println(errorMessage);
            } else {
                return id;
            }
        }
    }

}
