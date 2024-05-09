package Truong.it.server.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Truong.it.server.model.SinhVien;

public interface SinhVienRepository extends JpaRepository<SinhVien, Long>{
	List<SinhVien> findByTenKhoaContainingAndDiemTinGreaterThan(String tenKhoa, Double diemTin);
}
