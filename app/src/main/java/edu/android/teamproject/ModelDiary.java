package edu.android.teamproject;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import java.util.Date;

// Model 클래스
public class ModelDiary {

    private String id;
    private String weather;
    private String kimozzi;
    private String sendDate;
    private String receivedayDate;
    private String content;
    private int size;
    private int color;
    private String font;
    private String fileName;
    private String yourPhone;
    private String myphone;
    private String key;
    private long background;

    public ModelDiary(){}

    public ModelDiary(String id, String weather, String kimozzi, String sendDate, String receivedayDate, String content, int size, int color, String font, String fileName, String yourPhone, String myphone, String key, long background) {
        this.id = id;
        this.weather = weather;
        this.kimozzi = kimozzi;
        this.sendDate = sendDate;
        this.receivedayDate = receivedayDate;
        this.content = content;
        this.size = size;
        this.color = color;
        this.font = font;
        this.fileName = fileName;
        this.yourPhone = yourPhone;
        this.myphone = myphone;
        this.key = key;
        this.background = background;
    }

    public long getBackground() {
        return background;
    }

    public void setBackground(long background) {
        this.background = background;
    }

    public String getMyphone() {
        return myphone;
    }

    public void setMyphone(String myphone) {
        this.myphone = myphone;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getYourPhone() {
        return yourPhone;
    }

    public void setYourPhone(String yourPhone) {
        this.yourPhone = yourPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getKimozzi() {
        return kimozzi;
    }

    public void setKimozzi(String kimozzi) {
        this.kimozzi = kimozzi;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getReceivedayDate() {
        return receivedayDate;
    }

    public void setReceivedayDate(String receivedayDate) {
        this.receivedayDate = receivedayDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
