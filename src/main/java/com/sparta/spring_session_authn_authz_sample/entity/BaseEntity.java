package com.sparta.spring_session_authn_authz_sample.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 기본 엔티티. </p>
 * <p> 생성일과 수정일 컬럼을 추가하고 싶은 경우 이 클래스를 상속합니다. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    /**
     * 생성일.
     */
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /**
     * 수정일.
     */
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
