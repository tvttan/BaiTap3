package Truong.it.server.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Truong.it.server.model.SinhVien;
import Truong.it.server.repository.SinhVienRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SinhVienController {

	@Autowired
	SinhVienRepository sinhVienRepository;

	@GetMapping("/sinhviens")
	public ResponseEntity<List<SinhVien>> getAllSinhViens(@RequestParam(required = false) String tenKhoa,
	                                                      @RequestParam(required = false) Double diemTin) {
	    try {
	        List<SinhVien> sinhviens = new ArrayList<>();

	        if (tenKhoa == null && diemTin == null) {
	            // Trả về tất cả sinh viên nếu không có khoa và điểm
	            sinhviens.addAll(sinhVienRepository.findAll());
	        } else if (tenKhoa != null && diemTin != null ) {
	            // Tìm kiếm theo khoa và điểm trên 5
	            sinhviens.addAll(sinhVienRepository.findByTenKhoaContainingAndDiemTinGreaterThan(tenKhoa, diemTin));
	        } else {
	            // Trường hợp không hỗ trợ, có thể xử lý khác tùy thuộc vào yêu cầu cụ thể của bạn
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        if (sinhviens.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }

	        return new ResponseEntity<>(sinhviens, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/sinhviens/{ma}")
	public ResponseEntity<SinhVien> getSinhVienById(@PathVariable("ma") long ma) {
		Optional<SinhVien> sinhvienData = sinhVienRepository.findById(ma);
		if (sinhvienData.isPresent()) {
			return new ResponseEntity<>(sinhvienData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/sinhviens")
	public ResponseEntity<SinhVien> createSinhVien(@RequestBody SinhVien sinhvien) {
		try {
			SinhVien _sinhvien = sinhVienRepository
					.save(new SinhVien( sinhvien.getMa(),
										sinhvien.getHoTen(),
										sinhvien.getTenKhoa(),
										sinhvien.getGioiTinh(),
										sinhvien.getDiemTin()));
			return new ResponseEntity<>(_sinhvien, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/sinhviens/{ma}")
	public ResponseEntity<SinhVien> updateSinhVien(@PathVariable("ma") long ma, @RequestBody SinhVien sinhvien) {
		Optional<SinhVien> sinhvienData = sinhVienRepository.findById(ma);
		if (sinhvienData.isPresent()) {
			SinhVien _sinhvien = sinhvienData.get();
			_sinhvien.setMa(sinhvien.getMa());
			_sinhvien.setHoTen(sinhvien.getHoTen());
			_sinhvien.setTenKhoa(sinhvien.getTenKhoa());
			_sinhvien.setGioiTinh(sinhvien.getGioiTinh());
			_sinhvien.setDiemTin(sinhvien.getDiemTin());
			return new ResponseEntity<>(sinhVienRepository.save(_sinhvien), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/sinhviens/{ma}")
	public ResponseEntity<HttpStatus> deleteSinhVien(@PathVariable("ma") long ma) {
		try {
			sinhVienRepository.deleteById(ma);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}