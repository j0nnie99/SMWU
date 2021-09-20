#ggplot2 한글 깨짐 문제 해결
install.packages("extrafont")
library(extrafont)
font_import()
library(ggplot2)
theme_set(theme_grey(base_family="AppleGothic"))


#히스토그램 한글 깨짐 문제 해결
par(family="AppleGothic")



#1. weather.csv를 불러와서 weather 데이터 프레임 만들기
weather <- read.csv("weather.csv", stringsAsFactors = F, fileEncoding = "euc-kr")


#2. 측정 척도가 문자인 변수는 무엇인가?
### 요일 구분

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
var(weather$일강수량)
###[1] NA 이라고 출력되는데 이게 맞나?


#7. 요일과 요일.구분에 대해 빈도수를 각각 구하시오.
table(weather$요일)
table(weather$요일.구분)

#8. 요일과 요일.구분을 동시에 고려한 qplot을 그려 보시오.
library(ggplot2) #ggplot2 라이브러리 불러오기
qplot(data = weather, 요일, fill = 요일.구분)


#9. 평균기온과 평균.상대습도에 대해 히스토그램을 그리시오.
##평균기온: -20~50°C 구간에 대해 1°C 간격. 평균.상대습도: 0~100% 구간에 대해 1% 간격
hist(weather$평균기온, breaks = seq(-20, 50, by = 1))
hist(weather$평균.상대습도, breaks = seq(0, 100, by = 1))

