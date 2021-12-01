library(dplyr)
library(stringr)

raw_moon <- readLines("speech_moon.txt", encoding = "UTF-8")
head(raw_moon)

moon <- raw_moon %>% str_replace_all("[^가-힣]", " ") #한글이 아닌 정보 삭제
head(moon)
moon <- moon %>% str_squish()  #두 칸 이상의 연속된 공백 삭제
head(moon)

moon <- as_tibble(moon) #tibble로 변환
moon
