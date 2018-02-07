import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by lvbowen on 7/2/18.
 */
public class Main {
    private static Integer MAX_CONTAINER_NUMBER = 4000;
    private static Integer MAX_CLUSTER_NUMBER = 10;
    private static Integer MAX_POSITION_NUMBER = 200;
    private static Integer MAX_TIER_NUMBER = 5;
    private static Integer MAX_COLOR_CODE = 6;
    private static ArrayList<Container> containerArrayList = new ArrayList<Container>();
    private static Container[][][] storage = new Container[MAX_CLUSTER_NUMBER][MAX_POSITION_NUMBER][MAX_TIER_NUMBER];
    private static HashMap<Integer,String> color_dict = new HashMap<Integer,String>();
    public static Random random = new Random();

    public static void main(String [] args){
        createContainers();
        assignStorage();
        showStatus();
        //System.out.println(storage);
    }

    private static void createContainers()
    {
        for(int containerId = 0; containerId < MAX_CONTAINER_NUMBER; containerId++) {
            Container c = new Container(containerId, random.nextInt(MAX_COLOR_CODE));
            containerArrayList.add(c);
        }

        color_dict.put(0,"Red");
        color_dict.put(1,"Blue");
        color_dict.put(2,"Green");
        color_dict.put(3,"White");
        color_dict.put(4,"Black");
        color_dict.put(5,"Yellow");
    }

    private static void assignStorage(){
        for(int containerId = 0; containerId < MAX_CONTAINER_NUMBER; containerId++){
            Container c = containerArrayList.get(containerId);
            boolean flag = false;
            while(!flag) {
                int random_cluster_number = random.nextInt(MAX_CLUSTER_NUMBER);
                int random_position_number = random.nextInt(MAX_POSITION_NUMBER);
                int random_tier_number = random.nextInt(MAX_TIER_NUMBER);
                if (storage[random_cluster_number][random_position_number][random_tier_number] == null &&
                        (random_tier_number == 0 || storage[random_cluster_number][random_position_number][random_tier_number - 1] != null)) {
                    storage[random_cluster_number][random_position_number][random_tier_number] = c;
                    flag = true;
                }
            }
        }
    }

    private static void showStatus(){
        int[][] cluster_color_matrix = new int[MAX_CLUSTER_NUMBER][MAX_COLOR_CODE];
        int[][] tier_color_matrix = new int[MAX_TIER_NUMBER][MAX_COLOR_CODE];
        int[][][] cluster_tier_color_matrix = new int [MAX_CLUSTER_NUMBER][MAX_TIER_NUMBER][MAX_COLOR_CODE];
        int[] color_number_array = new int[MAX_COLOR_CODE];
        for(int cluster_number = 0; cluster_number < MAX_CLUSTER_NUMBER; cluster_number++){
            for(int position_number = 0; position_number < MAX_POSITION_NUMBER; position_number++){
                for(int tier_number = 0; tier_number < MAX_TIER_NUMBER; tier_number++){
                    if(storage[cluster_number][position_number][tier_number] != null){
                        Container c = storage[cluster_number][position_number][tier_number];
                        int colorCode = c.getColorCode();
                        cluster_color_matrix[cluster_number][colorCode]++;
                        color_number_array[colorCode]++;
                        tier_color_matrix[tier_number][colorCode]++;
                        cluster_tier_color_matrix[cluster_number][tier_number][colorCode]++;
                    }
                }
            }
        }

        System.out.println("===== General Color Info =====");
        for(int color_code = 0; color_code < MAX_COLOR_CODE; color_code++){
            System.out.println(color_dict.get(color_code) + " containers: " + color_number_array[color_code]);
        }
        System.out.println("===== Cluster Color Info =====");
        for(int cluster_number = 0; cluster_number < MAX_CLUSTER_NUMBER; cluster_number++){
            for(int color_code = 0; color_code < MAX_COLOR_CODE; color_code++){
                System.out.println("Cluster " + cluster_number + " has " + cluster_color_matrix[cluster_number][color_code] + " " + color_dict.get(color_code) + " containers" );
            }
            System.out.println();
        }
        System.out.println("===== Tier Color Info =====");
        for(int tier_number = 0; tier_number < MAX_TIER_NUMBER; tier_number++){
            for(int color_code = 0; color_code < MAX_COLOR_CODE; color_code++){
                System.out.println("Tier " + tier_number + " has " + cluster_color_matrix[tier_number][color_code] + " " + color_dict.get(color_code) + " containers" );
            }
            System.out.println();
        }
        System.out.println("===== Cluster Tier Color Info =====");
        for(int cluster_number = 0; cluster_number < MAX_CLUSTER_NUMBER; cluster_number++){
            for(int tier_number = 0; tier_number < MAX_TIER_NUMBER; tier_number++) {
                for (int color_code = 0; color_code < MAX_COLOR_CODE; color_code++) {
                    System.out.println("Cluster " + cluster_number + " Tier " + tier_number + " has "
                            + cluster_tier_color_matrix[cluster_number][tier_number][color_code] + " " + color_dict.get(color_code) + " containers");
                }
                System.out.println();
            }
        }
    }


}
