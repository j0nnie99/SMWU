#기본세팅 (한글깨짐 해결용)
install.packages("extrafont")
library(extrafont)
font_import()
library(ggplot2)
theme_set(theme_grey(base_family="AppleGothic"))

par(family="AppleGothic")

Sys.setlocale("LC_ALL", "ko_KR.UTF-8")
library(dplyr)
library(readxl)
library(tidyr)
library(foreign)
library(writexl)
library(psych)

middle <- read.csv("middle.csv", stringsAsFactors = F, fileEncoding = "euc-kr")

### 문제 1 ###
str(middle$date)

### 문제 2 date의 척도를 날짜(date)로 바꾸기 위해 어떤 함수를 사용해야 하는가?###
middle$date <- as.Date(weather$date)

### 문제 3 ###
middle$day <- weekdays(middle$date)
str(middle$day)

### 문제 4 ###
middle <- middle %>% relocate(day, .after = date)

### 문제 5 ###
table(is.na(middle$case))
table(is.na(middle$date))
table(is.na(middle$day))
table(is.na(middle$type))
table(is.na(middle$rainfall))#
table(is.na(middle$event))
table(is.na(middle$customer))#
table(is.na(middle$revenue))
table(is.na(middle$revenue1))
table(is.na(middle$revenue2))
table(is.na(middle$revenue3))
table(is.na(middle$revenue4))
table(is.na(middle$satisfaction))
table(is.na(middle$time))
table(is.na(middle$claim))
table(is.na(middle$error))

### 문제 6 ###
mid6 <- middle %>% filter(time>=30 & error >=10)

### 문제 7 ###
middle %>% filter((claim == 0) & (error == 0) )

### 문제 8 ###
middle %>% filter(day == "월요일" | day=="수요일")

### 문제 9 ###
hist(middle$revenue, breaks=seq(0,26000000, by=500000))

### 문제 10 ###
table((middle$day %in% c("월요일","화요일","수요일","목요일","금요일")) & middle$type=="휴일") 

### 문제 11 ###
middle$type <- ifelse(((middle$day %in% c("월요일","화요일","수요일","목요일","금요일")) & middle$type=="휴일"), middle$type=="공휴일", middle$type)
mean(middle$type)

### 문제 12 ###
middle$time <- ifelse(middle$time==0, NA, middle$time)
round(mean(middle$time, na.rm=T), digits=2)

### 문제 13 ###
table(middle$event)
mid_eve_0 <- middle %>% filter(middle$event==0 & middle$day=="월요일")
mean(mid_eve_0$revenue)

mid_eve_1 <- middle %>% filter(middle$event==1 & middle$day=="월요일")
mean(mid_eve_1$revenue) - mean(mid_eve_0$revenue)

### 문제 14 ###
middle <- middle %>% mutate(satis = ifelse(satisfaction < 4.0, "매우 불만족", ifelse(satisfaction < 4.3, "불만족", ifelse(satisfaction<4.5,"보통", ifelse(satisfaction < 4.75, "만족", "매우 만족")))))
table(middle$satis)
#만족 109, 매우만족 105, 매우불만족 5, 보통 63, 불만족 83
105 + 109 - 83 - 63 + 5

### 문제 15 ###
middle %>% group_by(satis) %>% summarise(mean_claim = mean(claim)) %>% arrange(mean_claim)

### 문제 16 ###
middle$problem <- middle$time + middle$claim + middle$error
table(is.na(middle$problem))

### 문제 17 ###
round(mean(middle$problem, na.rm=T),  digits=2)
middle$problem <- ifelse(is.na(middle$problem),round(mean(middle$problem, na.rm=T), digits=2), middle$problem)

#middle %>% group_by(problem) %>% summarise(count = n()) %>% mutate(rp_rate = count / ?)

### 문제 18 ###
no_rainfall <- middle %>% select(revenue4) %>%  filter(!is.na(middle$rainfall))
mean(no_rainfall$revenue4)
yes_rainfall <- middle %>% select(revenue4) %>%  filter(is.na(middle$rainfall))
mean(yes_rainfall$revenue4) - mean(no_rainfall$revenue4)

### 문제 19 ###


### 문제 20 ###
case10 <- middle %>% filter(!is.na(middle$customer) & middle$problem >= quantile(middle$problem, probs = c(0.9)))
round(mean(case10$satisfaction), digit=2)
