##문제1##
library(ggplot2)
midwest <- ggplot2::midwest
##문제2##
midwest <- midwest %>% mutate(percyouth = (1 - popadults/poptotal)*100)
##문제3##
midwest %>% select(county, percyouth) %>% arrange(-percyouth) %>% head(5)
##문제4##
midwest <- midwest %>% mutate(group = ifelse(percyouth >= 40, "large", ifelse(percyouth >= 30, "middle", "small")))
table(midwest$group)
##문제5##
midwest_add <- read.csv("midwest_add.csv", stringsAsFactors = F)
midwest <- left_join(midwest, midwest_add, by = c("county" = "region"))
##문제6##
midwest <- midwest %>% distinct(PID, county, state, .keep_all = T)
##문제7##
table(midwest$state)
midwest %>% group_by(state) %>% filter(!is.na(senior)) %>% summarise(sum_senior = sum(senior))
##문제8##
install.packages("tidyr")
library(tidyr)
table(is.na(midwest$senior))
midwest <- midwest %>% drop_na()
##문제9##
midwest %>% group_by(category) %>% summarise(mean_popasian = mean(popasian)) %>% arrange(mean_popasian) %>% head(3)
midwest %>% select(inmetro:last_col())

### 과제 문제

###문제1.주(state)별로 미성년인구백분율 평균을 구했을 때, 평균이 가장 낮은 지역과 평균을 각각 구하시오.
midwest %>% group_by(state) %>% summarise(percy_mean = mean(percyouth)) %>% arrange(percy_mean) %>% head(1)

###문제2.주(state)별로 아시아인구백분율 평균을 구했을 때, 평균이 가장 높은 지역과 평균을 각각 구하시오.
midwest %>% group_by(state) %>% summarise(asia_mean = mean((popasian/poptotal)*100)) %>% arrange(-asia_mean) %>% head(1)

###문제3. 아래 표를 참고하여 새로운 변수 grade를 만드시오.
senior_portion = midwest$senior/midwest$poptotal*100
midwest <- midwest %>% mutate(grade = ifelse(senior_portion >= 15, "very large", ifelse(senior_portion >= 8, "large", ifelse(senior_portion >= 5, "middle", "small"))))

###문제4. grade에 대한 빈도를 구하시오. 
midwest$grade <- factor(midwest$grade, levels = c("very large", "large", "middle", "small"))
table(midwest$grade)

###문제5. midwest_income.csv에는 county별로 1인당 연간 소득(단위: 달러) 변수 income을 포함하고 있다. 동일한 이름의 데이터 프레임을 만들고 midwest와 midwest_income을 통합한 후, income 전체 평균을 구하시오.
midwest_income <- read.csv("midwest_income.csv", stringsAsFactors = F, fileEncoding = "euc-kr")
midwest <- left_join(midwest, midwest_income, by="PID")
mean(midwest$income)

###문제6. 미성년인구비율이 40%이상이고, senior 비율이 5% 미만인 county의 연간소득 평균값은 얼마인가?
midwest_test <- midwest %>% filter(percyouth >= 40 & senior_portion < 5) %>% select(county, income)
mean(midwest_test$income)

midwest_new <- midwest %>% select(percyouth,senior_portion,income)
midwest_newnew <- midwest_new %>% filter(percyouth>=40) %>% filter(senior_portion<5)
mean(midwest_newnew$income)
