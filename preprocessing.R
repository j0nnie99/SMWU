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
exam <- exam %>% relocate(english, .after = math)

exam_science <- read.csv("exam_science.csv", stringsAsFactors = F)

exam <- left_join(exam, exam_science, by="id")
exam <- exam %>% relocate(Science, .before = total)


### left_join 실습
###연료별 가격 데이터 프레임(fuel_price) 만들기
fuel_price <- data.frame(fuel=c("CNG","diesel","ethanol","premium","regular"),fuel_price = c(2.35,2.38,2.11,2.76,2.22))
mpg <- left_join(mpg, fuel_price, by="fuel")

### 19-1. 구동방식별 가격 데이터 프레임(drv_price) 만들기
drv_price <- data.frame(driving=c(4,"forward","rear"), drv_price = c(40000, 30000, 50000))

### 19-2. mpg와 drv_price 합치기
mpg <- left_join(mpg, drv_price, by=c("drv"="driving"))
### 20. fuel_price는 fuel 뒤로, drv_price는 city 앞으로 이동시키기
mpg <- mpg %>% relocate(fuel_price, .after=fuel)
mpg <- mpg %>% relocate(drv_price, .before = city)
 

### bind_rows 함수 실습
exam_add <- read.csv("exam_add.csv", stringsAsFactors = F, fileEncoding = "CP949", encoding = "UTF-8")

#exam의 class는 범주형이고 exam_add의 class는 int형이어서 bind가 되지 않음. 아래 코드를 통해 class 자료형을 맞추어주는 것으로 해결
exam_add$class <- as.factor(exam_add$class)
exam <- bind_rows(exam, exam_add)

exam <- exam %>% distinct(id, class, total, .keep_all = T)

### 21. 통합된 exam 데이터 프레임에서 id=34는 id만 다를 뿐, id=29와 동일한 사례이므로 제거하시오.
exam <- exam %>% distinct(class, address, total, .keep_all = T)

### 22. average 변수 측정결과가 NA인 사례에 대해 세 과목에 대한 실제 평균값을 구해서 이 값으로 대체하시오.
exam_new <- exam
exam_new$average <- ifelse(is.na(exam_new$average), exam_new$total/3, exam_new$average)
exam$average <- ifelse(is.na(exam$average), exam$total/3, exam$average)
exam$average <- round(exam$average, digits=2)

### 23. science 변수 측정결과가 NA인 사례에 대해 다른 사례들의 science 평균값으로 대체하시오.
mean(exam$Science, na.rm = T)
exam$Science <- ifelse(is.na(exam$Science), mean(exam$Science, na.rm = T), exam$Science)



### midwest 데이터 프레임을 이용한 실습 ###

### 1. ggplot2에서 midwest 데이터를 불러와서 같은 이름의 데이터 프레임을 만드시오.
library(ggplot2)
midwest <- midwest

### 2. popadults는 해당 지역 성인인구, poptotal은 해당 지역 전체 인구를 의미한다. 지역별 '미성년 인구비율' 변수(percyouth)를 만드시오.
midwest <- midwest %>% mutate(percyouth = 1- (midwest$popadults / midwest$poptotal))

###3. percyouth가 제일 높은 다섯 개 지역(county)을 구하시오.
midwest %>% select(county, percyouth) %>% arrange(-percyouth) %>% head(5)

###4. 제시된 표를 참고하여 새로운 변수(group)를 만들고, 유형별 빈도를 구하시오.
midwest <- midwest %>% mutate(group = ifelse(percyouth >= 40, "large", ifelse(percyouth >= 30, "middle", "small")))
table(midwest$group)

###5. midwest_add.csv를 같은 이름의 데이터 프레임으로 저장한 후, midwest와 miwest_add를 county 변수와 region 변수를 기준으로 통합하시오.
midwest_add <- read.csv("midwest_add.csv", stringsAsFactors = F)
midwest <- left_join(midwest, midwest_add, by = c("county" = "region"))

###6. 통합 midwest 데이터 프레임에 중복 사례가 있다면 제거하시오.
midwest <- midwest %>% distinct(PID, county, state, .keep_all = T)

###7. 주(state)별로 senior 인구수 합계를 구하시오.
table(midwest$state)
midwest %>% group_by(state) %>% filter(!is.na(senior)) %>% summarise(sum_senior = sum(senior))

###8. tidyr 패키지 설치 후, 결측치가 있는 사례 제거하기
install.packages("tidyr")
library(tidyr)
table(is.na(midwest$senior))
midwest <- midwest %>% drop_na()

###9. 평균 아시아계 인구수가 가장 적은 category 세 개를 구하시오
midwest %>% group_by(category) %>% summarise(mean_popasian = mean(popasian)) %>% arrange(mean_popasian) %>% head(3)
midwest %>% select(inmetro:last_col())