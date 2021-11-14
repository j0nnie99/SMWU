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
