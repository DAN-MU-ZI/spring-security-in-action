package com.project.ssiach6ex1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 테이블의 기본 키를 나타내며 자동 증가하도록 정의됨
	@Column(nullable = false, length = 45)
	private String username; // 사용자 이름
	@Column(nullable = false, columnDefinition = "TEXT")
	private String password; // bcrypt 또는 scrypt 방식의 암호 해시
	@Enumerated(EnumType.STRING)
	private EncryptionAlgorithm algorithm; // 현재 레코드의 암호에 적용된 해시 방식에 따라 BCRYPT 또는 SCRYPT 값

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Authority> authorities = new ArrayList<>();
}
