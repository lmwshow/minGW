package 枚举;

/**
 * @Auther: minGW
 * @Date: 2018/7/12 16:55
 * @Description:
 */
public class enumTest {


    public enum Color{

        RED("red",1),GREEN("green",2),BLANK("blank",3),YELLOW("yellow",4);

        private String name;
        private int index;


        Color(String name, int index) {

            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }


    public static void main(String[] args){

        Color color = Color.RED;

        System.out.println(color.name);

        switch (color)
        {
            case RED:
                System.out.println("red");
                break;
            case GREEN:
                System.out.println("green");
                break;
            case BLANK:
                System.out.println("blank");
                break;
            case YELLOW:
                System.out.println("yellow");
                break;
        }

    }
}
