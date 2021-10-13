library(dplyr)
exam %>% filter(class == 1)

exam_male <- exam %>% filter(gender == "Male")
var(exam_male$english)

### 1. 1반, 2반, 3반 학생들의 수학시험 평균은 얼마인가?
exam_1 <- exam %>%  filter(class == 1)
mean(exam_1$math)
exam_2 <- exam %>%  filter(class == 2)
mean(exam_2$math)
exam_3 <- exam %>%  filter(class == 3)
mean(exam_3$math)

exam_123 <- exam %>% filter(class %in% c(1, 2, 3))
mean(exam_123$math)

### 2. 4반이 아닌 학생들 중에서 수학시험이 90점 이상이거나, 역사시험이 95점 이상인 학생들을 추출하여 새로운 데이터 프레임(exam_N4)을 만드시오.
#내 코드
exam_N4 <- exam %>% filter(class != 4 & (math >= 90 | history >= 95))
#교수님 코드
exam_N4 <- exam %>% filter(class != 4) %>% filter(math >= 90 | history >= 95)

### 3. 영어시험 성적이 상위 10%인 학생들만 추출하시오.
exam %>% filter(english >= quantile(english, probs = c(0.9)))
quantile(exam$english, probs = c(0.9)) #기준 점수 확인

### 4. 배기량이 4 이하인 자동차의 고속도로 연비평균과 배기량이 5 이상인 자동차의 고속도로 연비평균을 비교하시오.
mpg_4 <- mpg %>% filter(displ <= 4)
mpg_5 <- mpg %>% filter(displ >= 5)
mean(mpg_4$highway) > mean(mpg_5$highway)

### 5. 아우디와 도요타의 도심연비 평균을 비교하시오.
mpg_audi <- mpg %>% filter(manufacturer == "audi")
mpg_toyota <- mpg %>% filter(manufacturer == 'toyota')
mean(mpg_audi$city) > mean(mpg_toyota$city)

### 6. 세 회사(쉐보레, 포드, 혼다 자동차)의 고속도로연비 평균을 구하시오.
mpg_cfh <- mpg %>% filter(manufacturer %in% c("chevrolet", "ford", "honda"))
mean(mpg_cfh$highway)


### select 함수 ###
exam %>% select(class, math, english)
exam %>% select(-address)
exam %>% select(-contains("add"))

### 7. 1반 학생들만을 대상으로 성별과 수학점수를 추출
exam %>% filter(class == 1) %>% select(gender, math)

### 8. mpg에서 class와 city 두 개 변수만을 추출해서 새로운 데이터 프레임(mpg_cc)을 만드시오.
mpg_cc <- mpg %>% select(class, city)

### 9. 위에서 만든 데이터 프레임에서 suv 자동차와 compact 자동차의 도시연비 평균을 비교하시오.
mpg_ccSUV <- mpg_cc %>% filter(class == "suv")
mpg_ccCOM <- mpg_cc %>% filter(class == "compact")
mean(mpg_ccSUV$city) > mean(mpg_ccCOM$city)



### arrange 함수###

exam %>% arrange(math)
exam %>% arrange(-math)
exam %>% arrange(class, -math)

### 10. 아우디 모델 중에서 고속도로 연비가 가장 높은 상위 3개 모델은?
mpg_audi %>% arrange(desc(highway))
mpg %>% filter(manufacturer=="audi") %>% arrange(-highway) %>% head(3)


### mutate 함수 ###
exam <- exam %>% mutate(total = math + english + history)
exam <- exam %>% mutate(average = (math + english + history)/3)
exam <- exam %>% mutate(total = math + english + history, average = (math + english + history)/3)

exam$average <- round(exam$average, digits=2) #average값 소수점 자리수 조절
exam %>% mutate(test=ifelse(total>=180, "pass", "fail"))

### 11. 파생변수 sum(통합연비: 도심연비와 고속도로연비 합산)을 만드시오.
mpg <- mpg %>% mutate(sum = city + highway)

### 12. 통합연비의 평균은 얼마인가?
mean(mpg$sum)

### 13. 파생변수 avg(평균연비: 도심연비와 고속도로연비 평균)를 만들고, 평균 연비가 가장 높은 자동차 모델 세 개가 무엇인지 확인하시오.
mpg <- mpg %>% mutate(avg = (city + highway)/2) 
mpg %>% arrange(-avg) %>% head(3)



### group_by 함수와 summarise 함수 ###

#반 별로 학생수(빈도)와 수학점수의 평균, 표준편차 제시
exam %>% group_by(class) %>% summarise(n(), mean(math), sd(math))
exam %>% group_by(class) %>% summarise(count = n(), mean_math = mean(math), sd_math = sd(math))

exam_clshist <- exam %>% group_by(class, gender) %>% summarise(count = n(), mean_history = mean(history))

#세부 집단별 학생들의 구성비율을 보여주는 새로운 변수(perc) 만들기
exam_clshist <- exam_clshist %>% mutate(perc = count/sum(count))
exam_clshist <- exam_clshist %>% mutate(prop = count/sum(exam_clshist$count)) #prop는 전체 학생대비 비율 변수


### 14. 자동차 모델을 제조사별, 구동방식별로 구분한 후, 도심연비평균과 고속도로연비평균 요약 결과를 제시하시오.
mpg %>% group_by(manufacturer, drv) %>% summarise(mean(city), mean(highway)) %>% print(n=100)

### 15. 자동차 모델을 제조사별로 구분한 후, suv 모델에 대해 통합 연비 평균 상위 3개 모델을 구하시오.
mpg %>% group_by(manufacturer) %>% filter(class=="suv") %>% summarise(mean_sum = mean(sum)) %>% arrange(-mean_sum) %>% head(3)

### 16. 평균 배기량이 가장 높은 세 개 변속기를 제시하시오
#변수기별 구분 -> 평균 배기량 구하기 -> arrange -> head -> summarise
mpg %>% group_by(trans) %>% summarise(mean_displ = mean(displ)) %>% arrange(-mean_displ) %>% head(3)

### 17. 4기통 모델을 가장 많이 생산하는 업체 세 곳을 순서대로 구하시오.
mpg %>% group_by(manufacturer) %>% filter(cyl == 4) %>% summarise(count = n()) %>% arrange(-count) %>% head(3)



### left_join 함수 ###

vl <- c(1:30)
exam <- exam %>% mutate(id=vl)
exam <- exam %>% relocate(id, .before = address)
