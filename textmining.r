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

install.packages("tidytext")
library(tidytext)
word_space <- moon %>% unnest_tokens(input = value, output = word, token = "words")

word_space <- word_space %>% count(word, sort = T)
word_space <- word_space %>% filter(str_count(word) > 1)

top20 <- word_space %>% head(20)
library(ggplot2)

#맥북 한글 처리용
install.packages("extrafont")
library(extrafont)
font_import()
library(ggplot2)
theme_set(theme_grey(base_family="AppleGothic"))
###

ggplot(top20, aes(reorder(word, -n), n, fill = word)) + geom_bar(stat = "identity") + geom_text(aes(label = n ), hjust = -0.3) + labs(title = "문재인 출마 연설문 단어 빈도") + theme(title = element_text(size=12))

Sys.setlocale("LC_ALL", "ko_KR.UTF-8")
install.packages("ggwordcloud")
library(ggwordcloud)
ggplot(word_space, aes(label = word, size = n)) + geom_text_wordcloud(seed = 1234, family="AppleGothic") + scale_radius(limits = c(3,NA), range = c(3,10))

#colors + background
ggplot(word_space, aes(label = word, size = n, col = n)) + geom_text_wordcloud(seed = 1234, family="AppleGothic") + scale_radius(limits = c(3,NA), range = c(3,10)) + scale_color_gradient(low ="darkgreen", high="darkred") + theme_minimal()

#font
install.packages("showtext")
library(showtext)
font_add_google(name = "Nanum Gothic", family = "nanumgothic")
showtext_auto()

ggplot(top20, aes(reorder(word, -n), n, fill = word)) + geom_bar(stat = "identity") + geom_text(aes(label = n ), hjust = -0.3) + labs(title = "문재인 출마 연설문 단어 빈도") + theme(title = element_text(size=12), text = element_text(family = "nanumgothic"))
ggplot(word_space, aes(label = word, size = n, col = n)) + geom_text_wordcloud(seed = 1234, family="nanumgothic") + scale_radius(limits = c(3,NA), range = c(3,10)) + scale_color_gradient(low ="darkgreen", high="darkred") + theme_minimal()
