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


##형태소 분석을 위한 KoNLP 패키지 설치
#자바와 rJAVA 패키지 설치
install.packages("multilinguer")
remotes::install_gitlab("mrchypark/multilinguer")
library(multilinguer)
install_jdk()
install_java()

install.packages(c("stringr", "hash", "tau", "Sejong", "RSQLite", "devtools"), type = "binary")
install.packages("remotes")

install.packages("rJava")
install.packages("KoNLP", 
                 repos = c("https://forkonlp.r-universe.dev",
                           "https://cloud.r-project.org"),
                 INSTALL_opts = c("--no-multiarch")
)

library("KoNLP")
SimplePos09("안녕하신가요?")


###실습 시작
library(dplyr)
library(stringr)
library(tidytext)
library(ggplot2)
library(ggwordcloud)
library(showtext)

word_noun <- moon %>% unnest_tokens(input = value, output = word, token = extractNoun)


##명사 빈도 분석하기
#빈도수 상위 20개에 대해서 막대 그래프 그리기
word_noun <- word_noun %>% count(word, sort = T) %>% filter(str_csount(word) > 1 )

top20 <- word_noun %>% head(20)

#글자체 변경
library(showtext)
font_add_google(name = "Black Han Sans", family = "BHS")
showtext_auto()

#막대 그래프 그리기
ggplot(top20, aes(reorder(word, -n), n, fill = word)) + geom_bar(stat = "identity") + geom_text(aes(label = n ), hjust = -0.3) + labs(title = "문재인 출마 연설문 명사 빈도") + theme(title = element_text(size=12), text = element_text(family = "BHS"))

#워드 클라우드 그리기
ggplot(word_noun, aes(label = word, size = n, col = n)) + geom_text_wordcloud(seed = 1234, family="BHS") + scale_radius(limits = c(2,NA), range = c(3,15)) + scale_color_gradient(low ="darkgreen", high="darkred") + theme_minimal()

#특정 단어가 포함된 문장 찾기
sentences_moon <- raw_moon %>% str_squish() %>% as_tibble() %>% unnest_tokens(input=value, output=sentence, token = "sentences")

sentences_moon %>% filter(str_detect(sentence, "국민"))
sentences_moon %>% filter(str_detect(sentence, "일자리")) %>% print.data.frame(right = F)


## 문대통령 연설문 불러와 저장 ##
raw_moon <- readLines("speech_moon.txt", encoding = "UTF-8")
moon <- raw_moon %>% as_tibble() %>% mutate(president = "moon")

## 박전대통령 연설문 불러와 저장 ##
raw_park <- readLines("speech_park.txt", encoding = "UTF-8")
park <- raw_park %>% as_tibble() %>% mutate(president = "park")

 ## 두 연설문 통합과 전처리 및 토큰화 ##
bind_speeches <- bind_rows(moon, park) %>% relocate(president, .before = value)
library(stringr)
speeches <- bind_speeches %>% mutate(value = str_replace_all(value, "[^가-힣]", " "), value = str_squish(value))
library(tidytext)
library(KoNLP)
speeches <- speeches %>% unnest_tokens(input = value, output = word, token = extractNoun)

## 대통령별 단어 빈도수 구하기 ##
frequency <- speeches %>% count(president, word) %>% filter(str_count(word) > 1)

## 빈도수 상위 10개 단어 데이터 만들기 ##
top10 <- frequency %>% group_by(president) %>% slice_max(n, n = 10) %>% print(n = Inf)
top10 <- frequency %>% group_by(president) %>% slice_max(n, n = 10, with_ties = F) %>% print(n = Inf)

## 막대 그래프 그리기 ##
library(ggplot2)
ggplot(top10, aes(reorder(word, n), n, fill = president)) + geom_bar(stat = "identity") + coord_flip() + facet_wrap(~president)
ggplot(top10, aes(reorder(word, n), n, fill = president)) + geom_bar(stat = "identity") + coord_flip() + facet_wrap(~president, scales = "free_y")

# park에서 국민 단어 제외하기 #
top10 <- frequency %>% filter(word != "국민") %>% group_by(president) %>% slice_max(n, n = 10, with_ties = F) %>% print(n = Inf)

# 그래프별로 X축 항목 결과값을 다르게 하기 #
library(tidytext)
ggplot(top10, aes(reorder_within(word, n, president), n, fill = president)) + geom_bar(stat = "identity") + coord_flip() + facet_wrap(~president, scales = "free_y")
ggplot(top10, aes(reorder_within(word, n, president), n, fill = president)) + geom_bar(stat = "identity") + coord_flip() + facet_wrap(~president, scales = "free_y") + scale_x_reordered() + labs(x = NULL)

### 오즈비 구하기 ###
library(dplyr)
library(ggplot2)
library(tidyr)
library(tidytext)
library(stringr)

## long form data를 wide form data로 변경하기 ##
df_long <- frequency %>% group_by(president) %>% slice_max(n, n = 10) %>% filter(word %in% c("국민", "우리", "정치", "행복"))
df_wide <- df_long %>% pivot_wider(names_from = president, values_from = n)
df_wide <- df_long %>% pivot_wider(names_from = president, values_from = n, values_fill = list(n=0))
frequency_wide <- frequency %>% pivot_wider(names_from = president, values_from = n, values_fill = list(n=0))

## 오즈비 구하기 ##
frequency_wide <- frequency_wide %>% mutate(ratio_moon = ((moon + 1)/(sum(moon + 1))), ratio_park = ((park + 1)/(sum(park + 1))))
frequency_wide <- frequency_wide %>% mutate(odds_ratio = ratio_moon / ratio_park)
frequency_wide %>% arrange(-odds_ratio) #moon에서 차지하는 비중이 더 높음
frequency_wide %>% arrange(odds_ratio) #오름차순. park에서 차지하는 비중이 더 높음
frequency_wide %>% arrange(abs(1-odds_ratio)) #비슷하게 차지하는 것

## top10 데이터 만들기 ## odd ratio가 큰 순서대로 10개, 작은 순서대로 10개
top10 <- frequency_wide %>% filter(rank(odds_ratio) <= 10 | rank(-odds_ratio) <= 10) %>% arrange(-odds_ratio)

## 막대 그래프 그리기 ##
top10 <- top10 %>% mutate(president = ifelse(odds_ratio > 1, "moon", "park"), n = ifelse(odds_ratio > 1, moon, park))

# 새로운 두 개 변수 만들기 #
ggplot(top10, aes(x = reorder_within(word, n, president), n, fill = president)) + geom_bar(stat = "identity") + coord_flip() + facet_wrap(~ president, scales = "free_y") + scale_x_reordered() + labs(x = NULL)
ggplot(top10, aes(x = reorder_within(word, n, president), n, fill = president)) + geom_bar(stat = "identity") + coord_flip() + facet_wrap(~ president, scales = "free") + scale_x_reordered() + labs(x = NULL) # 그래프별로 축 설정하기 #

## 주요 단어가 사용된 문장 살펴보기 ##
speeches_sentence <- bind_speeches %>% as_tibble() %>% unnest_tokens(input = value, output = sentence, token = "sentences")
speeches_sentence %>% filter(president == "moon" & str_detect(sentence, "복지국가")) %>% print.data.frame(right = F)
speeches_sentence %>% filter(president == "park" & str_detect(sentence, "행복")) %>% print.data.frame(right = F)

#중요도가 비슷한 단어 살펴보기
frequency_wide %>% arrange(abs(1 - odds_ratio)) %>% head(10)
frequency_wide %>% filter(moon >= 5 & park >= 5) %>% arrange(abs(1 - odds_ratio)) %>% head(10)
