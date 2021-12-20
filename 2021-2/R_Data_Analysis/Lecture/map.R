crime <- USArrests
library(tibble)
crime <- rownames_to_column(crime, var = "state")
crime$state <- tolower(crime$state)

install.packages("maps")
library(maps)
library(ggplot2)
states_map <- map_data("state")

install.packages("mapporj")
library(mapproj)
install.packages("ggiraphExtra")
library(ggiraphExtra)
ggChoropleth(data = crime, aes(fill=Murder, map_id=state), map = states_map, interactive = T)

install.packages("stringi")
library(stringi)
install.packages("devtools")
library(devtools)
install.packages("usethis")
install.packages("Rcpp", dependencies = TRUE)
devtools::install_github("cardiomoon/kormaps2014")
library(kormaps201)


install.packages("plotly")
library(plotly)
library(ggplot2)

p1 <- ggplot(data = mpg, aes(displ, highway, col = drv)) + geom_point()
ggplotly(p1)

p2 <- ggplot(data = mpg, aes(class, fill=fuel)) + geom_bar(position = "dodge")
ggplotly(p2)

p3 <- ggplot(data = mpg, aes(class, fill = fuel)) + geom_bar(position = "dodge")
ggplotly(p3)

