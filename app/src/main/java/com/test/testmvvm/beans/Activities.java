package com.test.testmvvm.beans;

public class Activities {
    private String name;
    private String time;
    private String img;
    private boolean checked = false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Activities{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", img='" + img + '\'' +
                ", checked=" + checked +
                ", intro='" + intro + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    private String intro;

}
