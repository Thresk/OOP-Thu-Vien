package employee;

import book.TacGia;
import execute.Menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NhanVien {
    final String idNhanVien = "NV" + String.format("%03d", DanhSachNhanVien.soLuong + 1);
    private String ho;
    private String ten;
    private Boolean gioiTinh;
    private LocalDate ngaySinh;
    private String soDienThoai;

    public NhanVien() {
        ho = "none";
        ten = "none";
        ngaySinh = LocalDate.now();
        gioiTinh = false;
        soDienThoai = "none";
    }

    public void NhanVien(String ho, String ten, Boolean gioiTinh, LocalDate ngaySinh, String soDienThoai) {
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
    }

    public String getIDNhanVien() {
        return idNhanVien;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }


    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public NhanVien taoNhanVien() {
        NhanVien NhanVien = new NhanVien();
        LocalDate ngaySinh = null;

        System.out.println("Nhap ho cua NhanVien:");
        NhanVien.setHo(Menu.input.nextLine());

        System.out.println("Nhap ten cua NhanVien:");
        NhanVien.setTen(Menu.input.nextLine());

        System.out.println("Nhap gioiTinh cua NhanVien (1: nam/0: nu):");
        NhanVien.setGioiTinh(Menu.input.nextInt() == 1);

        System.out.println("Nhap vao ngay thang nam sinh (ddMMyyyy) cua NhanVien:");
        DateTimeFormatter formatter;
        String chuoiNgaySinh;
        boolean vongLap;
        do {
            try {
                vongLap = false;
                chuoiNgaySinh = Menu.input.nextLine();
                formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                ngaySinh = LocalDate.parse(chuoiNgaySinh, formatter);
            } catch (Exception e) {
                System.out.println("Dinh dang khong hop le, vui long nhap lai!");
                vongLap = true;
            }
        } while (vongLap);

        System.out.println("Nhap vao so dien thoai cua NhanVien:");
        NhanVien.setSoDienThoai(Menu.input.nextLine());
        NhanVien.setNgaySinh(ngaySinh);
        return NhanVien;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-15s%-15s%-10s%-15s%-15s", idNhanVien, ho, ten, gioiTinh ? "nam" : "nu", ngaySinh, soDienThoai);
    }
}