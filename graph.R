library(dplyr)
library(ggplot2)

#한글깨짐해결용
install.packages("extrafont")
library(extrafont)
font_import()
library(ggplot2)
theme_set(theme_grey(base_family="AppleGothic"))

### 산점도 ###

### mpg 실습 문제
ggplot(mpg, aes(displ, highway)) + geom_point() + xlim(3, 6) + ylim(20, 30)
#color
ggplot(mpg, aes(displ, highway, color = fuel)) + geom_point()
#shape, size
ggplot(mpg, aes(displ, highway, color = drv)) + geom_point(aes(shape = drv, size= fuel))
#추세선 추가
ggplot(mpg, aes(city, highway, color = cyl)) + geom_point(aes(shape = drv)) + xlim(0, 30) + ylim(0, 40) + geom_smooth()

### midwest 실습문제
ggplot(midwest, aes(poptotal, popasian, color = state)) + geom_point(aes(shape = state)) + xlim(0, 350000) + ylim(0, 5000) + geom_smooth()
