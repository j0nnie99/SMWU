#csv 파일 불러와서 데이터 프레임 만들기 
exam <- read.csv("exam.csv", stringsAsFactors = F)

###여섯 개 함수로 탐색적 검토하기
head(exam, 10)
tail(exam, 8)
head(exam$gender, 7)
View(exam)
exam$id <- NULL #id 변수 지워주기
dim(exam)
str(exam)
exam$address <- as.factor(exam$address) #순차적으로 세 개 변수 처도를 문자형(or 정수형)에서 범주형으로 바꾸기
exam$gender <- as.factor(exam$gender)
exam$class <- as.factor(exam$class)
summary(exam)

###개별 기술 통계량 함수 사용하기
mean(exam$math)
round(mean(exam$math), digits = 2) #소수점 둘째자리까지 표현
var(exam$math)
sd(exam$math)
var(exam$history)

###히스토그램 그리기
hist(exam$math, breaks = seq(0, 100, by = 5)) #exam의 math에 대한 히스토그램. 최솟값 0, 최댓값 100, 간격 5
hist(exam$history, breaks = seq(0, 100, by = 5))
hist(exam$english, breaks = seq(0, 100, by = 5)) 

###table함수와 qplot함수를 통한 빈도수 확인하기
table(exam$address)
table(exam$gender)
table(exam$class)
table(exam$address, exam$gender)
table(exam$class, exam$address)

install.packages("ggplot2")
install.packages("ggplot2", dependencies = TRUE)
library(ggplot2)
qplot(data = exam, address, fill = gender)
install.packages("farver", dependencies=T) #39번줄 실행 안 돼서 추가 설치함 (Error in loadNamespace(x) : there is no package called ‘farver’)
qplot(data = exam, class, fill = address)


###excel 파일 불러오기
install.packages("readxl")
library(readxl)

exam_addition <- read_excel("exam_addition.xlsx", sheet = 1)
exam_addition$id <- NULL #id 변수 지워주기

###데이터 프레임 내보내기
write.csv(exam, file="exam1.csv")
