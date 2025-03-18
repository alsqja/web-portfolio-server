package com.example.webportfolio.domain.portfolio.service;

import com.example.webportfolio.domain.portfolio.dto.PortfolioResDto;
import com.example.webportfolio.domain.portfolio.entity.Portfolio;
import com.example.webportfolio.domain.portfolio.repository.PortfolioRepository;
import com.example.webportfolio.domain.user.entity.User;
import com.example.webportfolio.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;

    public PortfolioResDto create(MultipartFile file, String id) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String fileUrl = "https://" + bucketName + ".s3.amazonaws.com/" + fileName;

        try {
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (Exception e) {
            log.error("e: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
        }

        Portfolio portfolio = new Portfolio(user, fileUrl, fileName);

        return new PortfolioResDto(portfolioRepository.save(portfolio));
    }

    public List<PortfolioResDto> findByUserId(String id) {

        return portfolioRepository.findByUserId(id).stream().map(PortfolioResDto::new).toList();
    }
}
