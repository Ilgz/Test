package com.example.player2.utils;


import com.example.player2.models.Movie;

import java.util.ArrayList;
import java.util.List;


public class DataSource {


        public static List<Movie> getNewestMovie() {
            Strings strings=new Strings();
        List<Movie> listMovies = new ArrayList<>();
            listMovies.add(new Movie("Годзилла против Конга", strings.Gozilla, "https://tinyurl.com/yefbr3uo", "https://bit.ly/3qo64nZ","2021"));
            listMovies.add(new Movie("Мортал Комбат", strings.Moratal, "https://bit.ly/3xhTM2r", "https://bit.ly/2RAy6zt", "2021"));
            listMovies.add(new Movie("Игры Шпионов", strings.igryString,"https://bit.ly/3zyvQdk", "https://bit.ly/3bKVwsL", "2021"));
            listMovies.add(new Movie("Никто", strings.Nikto, "https://bit.ly/3iCK2fb", "https://bit.ly/33ZLHD8", "2021"));
            listMovies.add(new Movie("Заступник", strings.Zastupnik, "https://bit.ly/3wxTN2B", "https://bit.ly/3u7Mrkj", "2021"));


            listMovies.add(new Movie("Постуль Хаоса",strings.Postup , "https://bit.ly/3gtJZjh", "https://bit.ly/3fCaH9T", "2021"));
            listMovies.add(new Movie("Оно", strings.Ono, "https://bit.ly/3gtvhZA", "https://bit.ly/3wA8fqp", "2017"));
            listMovies.add(new Movie("Омерзительная Восьмерка ", strings.Omerzit, "https://bit.ly/2Ttbhy5", "https://bit.ly/3hQ1PiO", "2015"));

            listMovies.add(new Movie("Бэтмен", strings.Batman, "https://bit.ly/2Snm7pz", "https://bit.ly/3uy3f44","2015"));
            listMovies.add(new Movie("Мстители Финал", strings.Avengers, "https://bit.ly/3wkwpW3", "https://bit.ly/3wurGAF", "2019"));
            listMovies.add(new Movie("Интерстеллар", strings.Inters, "https://bit.ly/3iClN0E", "https://bit.ly/3oKJIMl", "2014"));
            listMovies.add(new Movie("1+1", strings.OnePone, "https://bit.ly/3iQG6b1", "https://bit.ly/3vhNBLn", "2011"));
            listMovies.add(new Movie("Лига Справедливости Зака Снайдера", strings.Liga, "https://bit.ly/3pS0ABE", "https://bit.ly/3ffSMWD", "2021"));

            return listMovies;
    }

    public static List<Movie> getPopularMovie() {
        Strings strings = new Strings();

        List<Movie> listMovies = new ArrayList<>();
        listMovies.add(new Movie("Локи", strings.Loki,"https://bit.ly/3j2dbAA","https://bit.ly/3qiv2Fh","2021"));
        listMovies.add(new Movie("Звездные войны:Бракованная партия", strings.Zvezdniy,"https://bit.ly/3j0bxj5","https://bit.ly/3gKcWZy","2021"));
        listMovies.add(new Movie("Дота:Кровь Дракона", strings.Dota,"https://bit.ly/3j7LWoe","https://bit.ly/3qimOwF","2021"));
        listMovies.add(new Movie("Сокол и Зимныи Солдат", strings.Zimsold,"https://bit.ly/3qfE5GK","https://bit.ly/3vRQmlP","2021"));
        listMovies.add(new Movie("Ванда/Вижн", strings.Vanda,"https://bit.ly/3gXdZ74","https://bit.ly/3gMIMoA","2021"));
        listMovies.add(new Movie("Заговор против Америки", strings.America,"https://bit.ly/3d1wCG1","https://bit.ly/3wQnSdD","2020"));
        listMovies.add(new Movie("Ход королевы", strings.HodKorolevy,"https://bit.ly/3xAYGYy","https://bit.ly/3xAYUPo","2020"));
        listMovies.add(new Movie("Самурай Джек", strings.Samuray,"https://bit.ly/3zOu4Vy","https://bit.ly/3iYsXg1","2001"));
        listMovies.add(new Movie("Восход Османской Империи", strings.Osman,"https://bit.ly/2TV5hPb","https://bit.ly/3j9pR8K","2020"));
        listMovies.add(new Movie("Отмена", strings.Undone,"https://bit.ly/3x1Xeih","https://bit.ly/3j1qVvv","2019"));
        listMovies.add(new Movie("Халифат", strings.Halifat,"https://bit.ly/3zN2Y0T","https://bit.ly/3wRyNUr","2020"));
        listMovies.add(new Movie("Бумажный дом", strings.BumDom,"https://bit.ly/3wNOKLo","https://bit.ly/3j1PCI5","2017"));
        listMovies.add(new Movie("Элита", strings.Elita,"https://bit.ly/3qzfyNj","https://bit.ly/3cXGcK3","2018"));
        return listMovies;

    }

    public static List<Movie> getCartoons() {
        Strings strings=new Strings();
        List<Movie> listMovies = new ArrayList<>();
        listMovies.add(new Movie("Семейка Крудс ",strings.Semeyka ,"https://bit.ly/3zN114E", "https://bit.ly/3xyYaKK","2020" ));
        listMovies.add(new Movie("Бен 10 против Вселенной",strings.Benten ,"https://bit.ly/3j38yGo", "https://bit.ly/3gKOwPn","2020" ));
        listMovies.add(new Movie("Лего Супергерои DC: Флэш",strings.Flash ,"https://bit.ly/3j1qEsp", "https://bit.ly/2SMZuuU","2018" ));
        listMovies.add(new Movie("Человек-Паук: Через вселенные ",strings.Spdierman ,"https://bit.ly/3xLaqHV", "https://bit.ly/3d2RRqY","2018" ));
        listMovies.add(new Movie("Ральф против Интернета ",strings.Ralf ,"https://bit.ly/3cZ6iME", "https://bit.ly/3wNjAUw","2021" ));
        listMovies.add(new Movie("Райя и последний дракон",strings.Raya ,"https://bit.ly/2StDJzO", "https://bit.ly/35NYfhN","2021" ));
        listMovies.add(new Movie("Душа",strings.Dusha , "https://bit.ly/3gsDOMl", "https://bit.ly/3yr1vgb","2020"));

        listMovies.add(new Movie("Губка боб", strings.Gubka, "https://bit.ly/35unztb", "https://bit.ly/2RsZ2Bm", "2020"));
        listMovies.add(new Movie("Скуби-Ду!", strings.Scooby, "https://bit.ly/2RVrHPG", "https://bit.ly/2Rzs0PT", "2020"));
        listMovies.add(new Movie("Митчеллы против Машин", strings.Mitchels, "https://bit.ly/3pS1vSC", "https://bit.ly/3u75j2L", "2021"));
        listMovies.add(new Movie("Тролли: Мировой тур", strings.Trolls, "https://bit.ly/3cEznwO", "https://bit.ly/3bIEHyx", "2020"));
        listMovies.add(new Movie("Вперед", strings.Vpered, "https://bit.ly/3cDG4iN", "https://bit.ly/2RAadbk", "2021"));

        return listMovies;
    }
   

    public static List<Movie> getChannels() {
        List<Movie> listChannels = new ArrayList<>();
        String ort = "Расписание:\n" + "05:00Телеканал \"Доброе утро\"\n" +
                "09:00Новости\n" +
                "09:25Телеканал \"Доброе утро\"\n" +
                "09:50Жить здорово!\n" +
                "10:55Модный приговор (Дело \"В загсе я сказала \"нет\", и нас расписали!\")\n" +
                "12:00Новости\n" +
                "12:15Время покажет\n" +
                "15:00Новости\n" +
                "15:15Давай поженимся!\n" +
                "16:00Мужское/Женское\n" +
                "18:00Вечерние новости\n" +
                "18:40На самом деле";
        listChannels.add(new Movie("Орт", ort,"https://bit.ly/3pPyhnp", "http://stream.euroasia.lfstrm.tv/perviy_evrasia/1/index.m3u8","2021"));
        listChannels.add(new Movie("Тнт", ort, "https://bit.ly/2TrIB8I", "https://edge04.beetv.kz/btv/SWM/TNT/TNT_576p_2000kbps.m3u8", "2021"));
        listChannels.add(new Movie("РенТв", ort, "https://bit.ly/3vkJJbN", "http://ad-hls-rentv.cdnvideo.ru/ren/smil:ren.smil/chunklist_b1024000.m3u8", "2021"));
        listChannels.add(new Movie("Стс", ort, "https://bit.ly/3vqDRhe", "http://zabava-htlive.cdn.ngenix.net/hls/CH_STS/bw2000000/variant.m3u8?version=2", "2021"));
        listChannels.add(new Movie("Россия24", ort, "https://bit.ly/2SY4PPZ", "http://cdnmg.secure.live.rtr-vesti.ru/hls/russia_24/playlist_3.m3u8","2021"));
        listChannels.add(new Movie("МатчТв", ort, "https://bit.ly/3vJdtit",  "http://195.158.29.129/live_360/live.stream+_360p.m3u8","2021"));
        return listChannels;
    }

}
