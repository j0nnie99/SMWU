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
