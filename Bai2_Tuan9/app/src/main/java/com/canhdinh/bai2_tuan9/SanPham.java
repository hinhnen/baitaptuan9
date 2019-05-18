package com.canhdinh.bai2_tuan9;

public class SanPham {
    private String masp;
    private String tensp;
    private String HinhAnh;

    @Override
    public String toString() {
        return "masp"+masp+"/n tensp"+tensp;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }


    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

}
