import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.io.File
import java.util.*
import javax.imageio.ImageIO

val needIndex = listOf(
    29,
    30,
    33,
    37,
    38,
    61,
    65,
    66,
    69,
    76,
    80,
    86,
    96,
    137,
    156,
    158,
    162,
    182,
    183,
    184,
    190,
    191,
    192,
    195,
    196,
    208,
    209,
    220,
    224,
    231,
    234,
    238,
    239,
    243,
    264,
    269,
    270,
    273,
    275,
    280,
    282,
    283,
    284,
    285,
    286,
    289,
    293,
    311,
    312,
    315,
    317,
    318,
    320,
    330,
    341,
    345,
    354,
    375,
    376,
    377,
    380,
    381,
    384,
    399,
    400,
    402,
    416,
    423,
    427,
    437,
    443,
    452,
    459,
    460,
    461,
    463,
    467,
    472,
    474,
    475,
    476,
    490,
    492,
    495,
    496,
    501,
    506,
    507,
    509,
    510,
    512,
    514,
    519,
    530,
    539,
    545,
    549,
    550,
    555,
    556,
    557,
    559,
    561,
    562,
    563,
    564,
    567,
    569,
    570,
    580,
    584,
    587,
    592,
    595,
    606,
    621,
    664,
    666,
    673,
    674,
    677,
    680,
    683,
    685,
    690,
    694,
    704,
    707,
    710,
    721,
    723,
    731,
    732,
    740,
    750,
    752,
    755,
    758,
    759,
    763,
    769,
    798,
    808,
    809,
    819,
    821,
    829,
    830,
    833,
    834,
    843,
    850,
    856,
    859,
    867,
    868,
    870,
    905,
    906,
    911,
    922,
    943,
    944,
    962,
    979,
    988,
    990,
    991,
    998,
    1007,
    1008,
    1012,
    1022,
    1032,
    1071,
    1073,
    1074,
    1075,
    1076,
    1077,
    1078,
    1079,
    1086,
    1092,
    1106,
    1109,
    1128,
    1149,
    1150,
    1161,
    1190,
    1206,
    1219,
    1294,
    1299,
    1300,
    1301,
    1319,
    1321,
    1330,
    1332
)

const val filePath = "/Users/hyukim/Desktop/intelliJ/logo/src/main/resources/url2_save10.csv"
const val filePathResult = "/Users/hyukim/Desktop/intelliJ/logo/src/main/resources/url2_save11.csv"

// csv 에 있는 파일의 https 링크를 읽어서 파일로 저장
suspend fun ktorClient() {
    val httpsIndex = mutableListOf<Int>()
    println(needIndex.size)
    val client = HttpClient(CIO)
    val csv = File(filePath)
    val rows = csvReader().readAll(csv)
    var count = 0
    rows.forEachIndexed { index, row ->
//        ImageIO
        if (row[1] == "임혁" && row[2].startsWith("http")) {
            if (!row[2].startsWith("https")) {
                println(index)
                val httpResponse: HttpResponse = client.get(row[2])
                val responseBody: ByteArray = httpResponse.receive()
                val base64String = "data:image/png;base64," + Base64.getEncoder().encodeToString(responseBody)
                val toMutableList = row.toMutableList()
                toMutableList[2] = base64String
                csvWriter().open(filePathResult, append = true) {
                    writeRow(toMutableList)
                }

                delay(1000)
                count++
            } else {
                httpsIndex.add(index)
                csvWriter().open(filePathResult, append = true) {
                    writeRow(row)
                }
            }
        } else {
            csvWriter().open(filePathResult, append = true) {
                writeRow(row)
            }
        }
    }
    client.close()
    println(count)
}


suspend fun main(args: Array<String>) = coroutineScope {

    ktorClient()

//    System.setProperty("webdriver.chrome.driver", "/Users/hyukim/Downloads/chromedriver")
//    println("main")
//
//    val file = File("/Users/hyukim/Desktop/intelliJ/logo/src/main/resources/trim_name_varchar.csv")
//    val rows = csvReader().readAll(file)
//    launch {
//        println("start")
//        val driver = ChromeDriver()
//        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
//        try {
////            val bufferedWriter = File("/Users/hyukim/Desktop/intelliJ/logo/1/url2.txt").bufferedWriter()
//            csvWriter().open("/Users/hyukim/Desktop/intelliJ/logo/1/url3.csv", append = true) {
//                writeRow(
//                    listOf(
//                        "기관명",
//                        "index",
//                        "0",
//                        "1",
//                        "2",
//                        "3",
//                        "4",
//                        "5",
//                        "6",
//                        "7",
//                        "8",
//                        "9",
//                        "10",
//                        "11",
//                        "12",
//                        "13",
//                        "14",
//                        "15",
//                        "16",
//                        "17",
//                        "18",
//                        "19",
//                        "20",
//                        "21",
//                        "22",
//                        "23",
//                        "24",
//                        "25",
//                        "26",
//                        "27",
//                        "28",
//                        "29"
//                    )
//                )
//            }
//            rows.forEachIndexed { index, row ->
//                if (needIndex.contains(index)) {
//                    val r = row.first()
//                    driver.get("https://www.google.co.kr/imghp?hl=ko")
//                    driver.findElement(By.name("q")).sendKeys("$r 로고" + Keys.ENTER)
//                    val results = wait.until(presenceOfAllElementsLocatedBy(By.cssSelector("div a div img")))
//                    withContext(Dispatchers.IO) {
//                        val list = mutableListOf<String?>(r, index.toString())
//                        results.slice(0..20).forEachIndexed { index, result ->
//                            val src = result.getAttribute("src")
//                            list.add(src)
////                        bufferedWriter.append("${r} && ${index} && ${src}\n")
////                        val parseBase64Binary = DatatypeConverter.parseBase64Binary(src.substring(src.indexOf(',') + 1))
////                        val read = ImageIO.read(ByteArrayInputStream(parseBase64Binary))
////                        val p = src.substring(src.indexOf('/') + 1, src.indexOf(';'))
////                        ImageIO.write(read, p, File("/Users/hyukim/Desktop/intelliJ/logo/1/" + "${r}_${index}.${p}"))
//                        }
//                        csvWriter().open("/Users/hyukim/Desktop/intelliJ/logo/1/url3.csv", append = true) {
//                            writeRow(list)
//                        }
//                    }
//                    delay(2000)
//                }
//            }
//        } finally {
//            driver.quit()
//        }
//        println("end")
//    }
//    // Try adding program arguments via Run/Debug configuration.
//    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//    println("Program arguments: ${args.joinToString()}")
}