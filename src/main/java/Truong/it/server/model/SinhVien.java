package Truong.it.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_sinhvien")
public class SinhVien {
	@Id
	@Column(name = "masv")
	private Long ma;
	@Column(name = "hoten")
	private String hoTen;
	@Column(name = "tenkhoa")
	private String tenKhoa;
	@Column(name = "gioitinh")
	private String gioiTinh;
	@Column(name = "diemtin")
	private Double diemTin;
	private String tinhTrang;
	
	public SinhVien(Long ma, String hoTen, String tenKhoa, String gioiTinh, Double diemTin) {
		super();
		this.ma = ma;
		this.hoTen = hoTen;
		this.tenKhoa = tenKhoa;
		this.gioiTinh = gioiTinh;
		this.diemTin = diemTin;
	}
	
	public SinhVien() {
		super();
	}

	public Long getMa() {
		return ma;
	}
	public void setMa(Long ma) {
		this.ma = ma;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getTenKhoa() {
		return tenKhoa;
	}
	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public Double getDiemTin() {
		return diemTin;
	}
	public void setDiemTin(Double diemTin) {
		this.diemTin = diemTin;
	}
	
	
	public String getTinhTrang() {
		if (diemTin >= 8) {
	        return "Giỏi";
	    } else if (diemTin >= 5 && diemTin < 8) {
	        return "Trung bình";
	    } else {
	        return "Kém";
	    }
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	
}
