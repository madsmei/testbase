package com.zg.moshi;

/**
 * @Description: 建造者 模式 演示
 * @Date 2020/2/21
 * @Version V1.0
 * @Author Mads
 **/
public class Food {
    private String name;
    private String reliang;
    private String zhongliang;
    private String color;
    private String xingzhuang;

    public Food(FoodBuilder builder) {
        this.name = builder.name;
        this.reliang = builder.reliang;
        this.zhongliang = builder.zhongliang;
        this.color = builder.color;
        this.xingzhuang = builder.xingzhuang;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", reliang='" + reliang + '\'' +
                ", zhongliang='" + zhongliang + '\'' +
                ", color='" + color + '\'' +
                ", xingzhuang='" + xingzhuang + '\'' +
                '}';
    }

    public Food(String name) {
        this.name = name;
    }

    public static final class FoodBuilder {
        private String name;
        private String reliang;
        private String zhongliang;
        private String color;
        private String xingzhuang;

        public FoodBuilder() {
        }

        public FoodBuilder(String name) {
            this.name = name;
        }

        public FoodBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FoodBuilder reliang(String reliang) {
            this.reliang = reliang;
            return this;
        }

        public FoodBuilder zhongliang(String zhongliang) {
            this.zhongliang = zhongliang;
            return this;
        }

        public FoodBuilder color(String color) {
            this.color = color;
            return this;
        }

        public FoodBuilder xingzhuang(String xingzhuang) {
            this.xingzhuang = xingzhuang;
            return this;
        }

        public Food build() {
            return new Food(this);
        }
    }

    public static void main(String[] args) {
        Food food = new FoodBuilder("面包").color("金黄").reliang("100").build();
        System.out.println(food);
    }
}
