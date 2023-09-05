package com.clonecoding.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.clonecoding.entity
 * fileName       : PressReleasBasRepository
 * author         : hagjoon
 * date           : 2023-09-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        hagjoon       최초 생성
 */
@Repository
public interface PressReleasBasRepository extends JpaRepository<PressReleasBas, Integer> {
}
