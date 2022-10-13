package RSAPI;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(PayloadAddPlace.CoursePrice());

        //two brackets for the array
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //print purchase amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        String coursesTitle = js.get("courses[2].title");
        System.out.println(coursesTitle);

        for (int i = 0; i < count; i++) {
            String courseTitles = js.get("courses[" + i + "].title");
            System.out.println(courseTitles);
            System.out.println(js.get("courses[" + i + "].price").toString());
        }

        System.out.println("print no of copies of sold by RPA Course");

        for (int i = 0; i < count; i++) {
            String courseTitles = js.get("courses[" + i + "].title");
            if (courseTitles.equalsIgnoreCase("RPA")) {
               int copies =  js.get("courses[" + i + "].copies");
                System.out.println(copies);
                break;

            }
        }
    }
}