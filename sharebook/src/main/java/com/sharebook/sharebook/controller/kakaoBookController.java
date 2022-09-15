package com.sharebook.sharebook.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sharebook.sharebook.domain.Book;

import org.json.JSONArray;
import org.json.JSONObject;

@RestController
public class kakaoBookController {

	private final String url = "https://dapi.kakao.com/v3/search/book";
	private final String key = "46988fda87fb89c623200cd06a6b623d";

	@GetMapping("/book/search/api.do")
	public ModelAndView viewCreateBook1(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("thymeleaf/searchAPI");
		return mav;
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/bookapi", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView callapihttp(String query) {
		ModelAndView mav = new ModelAndView();
		String jsonText = null;
		ResponseEntity<Map> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Authorization", "KakaoAK " + key); // Authorization 설정

			HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
			URI targetUrl = UriComponentsBuilder.fromUriString(url) // 기본 url
					.queryParam("query", query).queryParam("size", 20) // 인자
					.build().encode(StandardCharsets.UTF_8) // 인코딩
					.toUri();

			ResponseEntity<?> resultMap = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Object.class);
			ObjectMapper mapper = new ObjectMapper();
			jsonText = mapper.writeValueAsString(resultMap.getBody());

			JSONObject jsonObject = new JSONObject(jsonText);
			JSONArray jsonArray = jsonObject.getJSONArray("documents");
			List<Book> bookList = new ArrayList<>();
			;

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				String title = obj.getString("title");

				String author = obj.getJSONArray("authors").toString();
				author = author.replace("[\"", "");
				author = author.replace("\"]", "");

				if (author.contains("/")) {
					int idx = author.indexOf("/");
					author = author.substring(0, idx);
					author = author + " 외";
				}

				if (author.contains(",")) {
					int idx = author.indexOf(",");
					author = author.substring(0, idx - 1);
					author = author + " 외";
				}

				String publisher = obj.getString("publisher");
				String imgUrl = obj.getString("thumbnail");
				System.out.println(obj.getString("thumbnail"));
				String description = obj.getString("contents");
				String isbn = obj.getString("isbn").split(" ")[0];
				String publishYear = String.valueOf(obj.getString("datetime")).substring(0, 4);
				Book book = Book.builder().title(title).author(author).publisher(publisher).image(imgUrl)
						.description(description).isbn(isbn).publishYear(publishYear).build();
				bookList.add(book);
			}
			mav.setViewName("thymeleaf/searchPartial");

			List<List<Book>> totalBookList = new ArrayList<>();
			if (bookList.size() > 0) {
				System.out.println("API 검색 결과 있음" + bookList.size());
				for (int i = 0; i <= (bookList.size() / 5); i++) {
					List<Book> partialBookList = new ArrayList<>();
					if (i == (bookList.size() / 5)) {
						if (bookList.size() % 5 == 0) {
							break;
						} else {
							for (int j = 0; j < (bookList.size() % 5); j++) {
								partialBookList.add(bookList.get((i * 5) + j));
							}
						}
					} else {
						for (int j = 0; j < 5; j++) {
							partialBookList.add(bookList.get((i * 5) + j));
						}
					}

					totalBookList.add(partialBookList);
				}
			}

			mav.addObject("bookList", totalBookList);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mav; // 내용 반환
	}
}
