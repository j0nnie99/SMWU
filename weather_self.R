#ggplot2 한글 깨짐 문제 해결
install.packages("extrafont")
library(extrafont)
font_import()
library(ggplot2)
theme_set(theme_grey(base_family="AppleGothic"))


#히스토그램 한글 깨짐 문제 해결
par(family="AppleGothic")

#요일을 한글로 설정하기 위해 시스템을 한글로 세팅
Sys.setlocale("LC_ALL", "ko_KR.UTF-8")

#1. weather.csv를 불러와서 weather 데이터 프레임 만들기
weather <- read.csv("weather.csv", stringsAsFactors = F, fileEncoding = "euc-kr")


#2. 측정 척도가 문자인 변수는 무엇인가?
str(weather)
### 일시, 요일.구분

#3. '일시' 변수에 대해 측정 척도를 문자에서 날짜(date)로, '요일 구분'에 대해 범주(factor)로 바꾸시오
## 힌트 요일로 변경 - df$var <- as.Date(df$var) /범주로 변경 - df$var <- as.factor(df$var)
weather$일시 <- as.Date(weather$일시)
weather$요일.구분 <- as.factor(weather$요일.구분)
str(weather) #타입 확인용


#4. 일시 변수를 이용해서 요일(월~일요일)을 파악한 후 이를 '요일'이라는 이름의 변수에 저장하시오. 그리고 '요일'변수의 척도를 문자에서 범주로 바꾸시오
## 힌트: 내장함수인 weekdays()를 활용함
weather$요일 <- weekdays(weather$일시)
weather$요일 <- as.factor(weather$요일)


#5. 14개 변수에 대해 summary()를 통해 검토해 보시오.
summary(weather)


#6. ‘일강수량’ 변수에 대해 분산을 구해 보시오.
weather$일강수량[is.na(weather$일강수량)] <- 0
summary(weather)
var(weather$일강수량)
###[1] NA 이라고 출력되는데 이게 맞나?
###na -> 0 으로 변경해서 [1] 213.3963 결과값 얻어냄

#교수님 답 (아예 결측치 제거)
var(weather$일강수량, na.rm = T)


#7. 요일과 요일.구분에 대해 빈도수를 각각 구하시오.
##요일의 경우, 가나다 순서대로 된 정렬을 요일별로 바꿀 수는 없을까?
table(weather$요일)
table(weather$요일.구분)
weather$요일 <- factor(weather$요일, levels = c("월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"))
#weather$요일 <- factor(weather$요일, levels = c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"))

#8. 요일과 요일.구분을 동시에 고려한 qplot을 그려 보시오.
library(ggplot2) #ggplot2 라이브러리 불러오기
qplot(data = weather, 요일, fill = 요일.구분)


#9. 평균기온과 평균.상대습도에 대해 히스토그램을 그리시오.
##평균기온: -20~50°C 구간에 대해 1°C 간격. 평균.상대습도: 0~100% 구간에 대해 1% 간격
hist(weather$평균기온, breaks = seq(-20, 50, by = 1))
hist(weather$평균.상대습도, breaks = seq(0, 100, by = 1))



#기술통계량 관련 추가
install.packages("pastecs")
library(pastecs)
stat.desc(exam$math)

install.packages("psych")
library(psych)
describe(exam$math)



#######추가예제1#######

## 1. 평균기온이 27도 이상인 날이 몇 일인가?
table(weather$평균기온 >= 27)
## 2. 평균기온이 10도 이상이고, 20도 이하인 날이 몇 일인가?
table(weather$평균기온 >= 10 & weather$평균기온 <= 20)
## 3. 일강수량이 0인 날은 얼마나 되는가?
table(weather$일강수량 == 0)

## 4. 일강수량 변수의 측정값이 NA(결측치)인 날은 얼마나 되는가?
table(is.na(weather$일강수량))
table(!is.na(weather$일강수량))
#summary(weather$일강수량)

## 5. 월요일, 화요일, 수요일은 몇 일인가?
table(weather$요일 %in% c("월요일","화요일","수요일"))
## 6. 최고기온이 30도 보다 높고, 평균.상대습도는 80보다 높은 날은 몇 일인가?
table(weather$최고기온 > 30 & weather$평균.상대습도 > 80)
## 7. 최저기온이 -10도보다 낮거나, 합계.일조시간이 1시간 미만인 날은 몇 일인가?
table(weather$최저기온 < -10 | weather$합계.일조시간 < 1)

#######추가예제2#######
weather_new <- weather
library(dplyr)
#8. 요일.구분은 요일구분으로, 평균.현지기압은 평균기압으로 변수명을 바꾸시오
weather_new <- rename(weather_new, 요일구분 = 요일.구분, 평균기압=평균.현지기압)

#9. 새로운 변수 요일구분과 관련해서 출력 순서를 기존의 평일-휴일에서, 휴일-평일로 변경하시오
table(weather_new$요일구분)
weather_new$요일구분 <- factor(weather_new$요일구분, levels = c("휴일", "평일"))

#10. 일강수량이 0으로 측정되는 경우 이 값을 NA로 바꾸시오
weather_new$일강수량 <- ifelse(weather_new$일강수량 == 0, NA, weather_new$일강수량)

#11. 평균기압에 대한 결측치는 몇 개인가?
table(is.na(weather_new$평균기압))

#12. 결측치를 제외한 평균기압 평균은 얼마인가? (유효숫자 소수 둘째자리)
#m <- mean(weather_new$평균기압, na.rm = T)
m <- round(mean(weather_new$평균기압, na.rm = T), digits=2)
#13. 평균기압이 NA인 경우, 이를 문제 12에서 구한 평균값으로 대체하시오
#내 코드
weather_new$평균기압 <- ifelse(weather_new$평균기압 == NA, m, weather_new$평균기압)
#교수님 코드(13번)
weather_new$평균기압 <- ifelse(is.na(weather_new$평균기압), m, weather_new$평균기압)

#12번 + 13번
weather_new$평균기압 <- ifelse(is.na(weather_new$평균기압), round(mean(weather_new$평균기압, na.rm = T), digits=2), weather_new$평균기압)
