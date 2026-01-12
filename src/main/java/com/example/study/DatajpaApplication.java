package com.example.study;

import com.example.study.entity.Member;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatajpaApplication {

	@Data
	static class HaveItemOutputDto {

		private final Long id;
		private final String category;
		private final Integer price;
		private final LocalDateTime payCreateTime;

		public HaveItemOutputDto(Long id, String category, Integer price, LocalDateTime payCreateTime) {
			this.id = id;
			this.category = category;
			this.price = price;
			this.payCreateTime = payCreateTime;
		}

	}

	public static void main(String[] args) {

		List<HaveItemOutputDto> items = List.of(
				new HaveItemOutputDto(
						1L, "A", 10000,
						LocalDateTime.of(2024, 1, 10, 10, 0)
				),
				new HaveItemOutputDto(
						2L, "A", 15000,
						LocalDateTime.of(2024, 1, 9, 9, 0)
				),
				new HaveItemOutputDto(
						3L, "B", 20000,
						LocalDateTime.of(2024, 1, 11, 11, 0)
				),
				new HaveItemOutputDto(
						4L, "B", 20000,
						null
				)
		);

		// 카테고리별로 묶고, 각 그룹 안에서 최신순 정렬.

		String username = "shim";
		System.out.println("username = " + username + 1);




//		SpringApplication.run(DatajpaApplication.class, args);
	}

}
