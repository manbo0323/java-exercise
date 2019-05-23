# Gain Miles Java Exercise

## Requirements:

Please use the following template to implement a function arithmetic():

```java
class Exercise {

    public static void main(String args[]) {
        System.out.println(this.arithmetic(args[0]));
    }
    
    private Integer arithmetic(String expr) {
        // Input: “1 + 2 + 3” will return 6
        // Input: “(1 + 2) * 3” will return 9
        // Input: “(1 +) * 3” will raise an Exception
        // Please put your code here
    }
}
``` 

You can assume the followings:
* The function will only handle integer, with range from -2147483648 to 2147483647
* You do not need to handle overflow
* You do need to check for invalid arithmetic expression and raise Exception

## compile / build, run
1. build using maven `mvn clean package`
2. run with `java -jar target/java-exercise-1.0-pkg.jar "1 + 2 + 3"`

