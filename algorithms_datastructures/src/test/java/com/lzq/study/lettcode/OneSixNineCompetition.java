package com.lzq.study.lettcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhengqiu on 2019/12/29.
 */
public class OneSixNineCompetition {

    public int[] sumZero(int n) {
        int[] result = new int[n];
        int i = 1;
        for (int j=1; j<=n; j++){
            if (j==n&&j%2!=0){
                result[j-1] = 0;
            }else{
                if (j%2!=0) {
                    result[j-1] = i;
                }
                else {
                    result[j-1] = 0-i;
                    i++;
                }
            }
        }
        return result;
    }

    public int game(int[] guess, int[] answer) {
        int result = 0;
            for (int i = 0; i < 3; i++){
            if (guess[i] == answer[i]) result++;
        }
        return result;
    }

    @Test
    public void test01(){
        int[] cont = {3,2,0,2};
        int[] result = fraction(cont);
        Assert.assertTrue(result[0]==13&&result[1]==4);
        int[] cont1 = {0,0,3};
        result = fraction(cont1);
        Assert.assertTrue(result[0]==3&&result[1]==1);
    }

    public int[] fraction(int[] cont) {
        int len = cont.length;
        if (len==1){
            return new int[]{cont[0],1};
        }
        int fenZi = 1;
        int fenMu = cont[len-1];
        if (len >= 2){
            fenZi = 1 + fenMu * cont[len-2];
        }
        for (int i = len-3; i >= 0; i--){
            if (fenZi%fenMu==0){
                int temp = fenZi/fenMu;
                fenZi = 1 + cont[i] * temp;
                fenMu = temp;
            }else {
                int temp = fenZi;
                fenZi = fenZi * cont[i] + fenMu;
                fenMu = temp;
            }
        }
        int gcd = getGCD(fenZi,fenMu);
        fenZi = fenZi/gcd;
        fenMu = fenMu/gcd;
        return new int[]{fenZi,fenMu};
    }
    private int getGCD(int num1, int num2){
        int min = num1 > num2 ? num2 : num1;
        int gcd = 1;
        for (int i = 2; i <= min; i++){
            if (num1 % i == 0 && num2 % i == 0){
                gcd = i;
            }
        }
        return gcd;
    }

    @Test
    public void test02(){
        String command = "UUURUURRUUURURRURRRRRRUUURURRRRRRRRURRRRRURURRURRURRRRUURUUURRURURRRURURRUUURURUUURRRURURRRURUURUUURRURRURURURRUUURRUUURUUUUURUUUURUUURUURURRRURRRRRUUURURRRUURURRRURURURURUUURURRURRURRUUUURRUUURUURUUUUUURUUURRRUUUUURURURURURRRRRURURURUUUURUUUURUUURRUURURRUUURURURUURRRUUUURURURRRRURRURUUUURUUUUUUURURURRUUUUUURRUUURUURUURUUURRURUUURURRRURRRUUUURRUURUURRRRURRUURURUURRRURRRRURRRRRRRURRUURRRUUUUURRRURRRURRRRRRRRURUUURURRURUURRUURRUURURRRRURURURUURURUURURRURUUURURUUURRRRRURRUURURRRURURRRRRURUUURURUURUUUUURRRRURRRURRRURURURURRRUURRRURURRURRUURRRRUURRRUURRRURRRRURURRURURUUUUURRRUUUURRURRRURRRURRRRRUURUUURUURRRURRRRRURRRRURURRRUUURRUUURUUURUURUURUUUUUURUURURRUURUURRRRUURURRRUURRRRRURRURUURRRURUURURURRURUURRURUURRRRRURRRRURRRRURRURUURRURUUUURUUUUUUURURURUUURRRUURUUURRRRRUURRRURRUURRURRUURRUUUURRUURRUUURURRUUURURUUUUURUUUURRRRURURRRUURURURRURRURRURURUURUUUURUUUURRUUURRUURRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR";
        int[][] obstacles = new int[][]{{35340729, 26552041}, {41315902, 31041191}, {35435801, 26623424}, {35985598, 27036459}, {35343672, 26554242}, {39172512, 29430893}, {35344039, 26554511}, {35343095, 26553813}, {35344210, 26554671}, {35341724, 26552776}, {53570757, 40248461}, {49650290, 37302972}, {35344225, 26554671}, {35344116, 26554577}, {35343163, 26553813}, {43181981, 32443229}, {35340116, 26551573}, {35345293, 26555471}, {35342017, 26552955}, {35343403, 26554032}, {48113629, 36148415}, {49088908, 36881155}, {35343396, 26554025}, {46376632, 34843392}, {35343124, 26553813}, {35345139, 26555340}, {35340609, 26551935}, {35344125, 26554587}, {35342526, 26553384}, {35340096, 26551559}, {35342909, 26553660}, {35344181, 26554647}, {35343624, 26554228}, {35345879, 26555910}, {45549286, 34221787}, {35344726, 26555044}, {53645565, 40304663}, {53711241, 40354018}, {35345468, 26555529}, {35344558, 26554905}, {35340094, 26551557}, {35344566, 26554910}, {49936861, 37518238}, {35344721, 26555041}, {35340300, 26551668}, {35343612, 26554219}, {35345990, 26555958}, {48864075, 36712300}, {35343670, 26554242}, {35341868, 26552899}, {35340561, 26551903}, {35342412, 26553300}, {35340596, 26551928}, {53152734, 39934366}, {51471112, 38670936}, {35344165, 26554622}, {37310888, 28032166}, {35344563, 26554907}, {51998831, 39067472}, {35345942, 26555958}, {35343409, 26554038}, {44398802, 33357458}, {35343025, 26553769}, {35342372, 26553257}, {51983299, 39055749}, {49808449, 37421792}, {35345780, 26555818}, {47239503, 35491699}, {35345760, 26555803}, {35345718, 26555773}, {35345309, 26555483}, {41693378, 31324812}, {35345800, 26555834}, {37106471, 27878587}, {51738987, 38872228}, {53521119, 40211195}, {35345963, 26555958}, {53895124, 40492187}, {35341169, 26552358}, {35340761, 26552077}, {35344643, 26554967}, {45166191, 33933981}, {35340803, 26552097}, {35344116, 26554578}, {35344010, 26554496}, {45833092, 34435021}, {35340641, 26551957}, {35342863, 26553628}, {43773571, 32887717}, {35340177, 26551631}, {35342427, 26553305}, {35631017, 26770060}, {35340665, 26551986}, {35344620, 26554945}, {47197241, 35459938}, {49746170, 37374979}, {42643609, 32038776}, {39528764, 29698548}, {36296327, 27269968}, {46741544, 35117551}, {38695129, 29072237}, {35345332, 26555511}, {35342431, 26553316}, {36255177, 27239028}, {35344089, 26554551}, {35342012, 26552955}, {35344662, 26554986}, {35345764, 26555805}, {43143202, 32414132}, {35341122, 26552318}, {40190518, 30195707}, {35340873, 26552097}, {46165530, 34684846}, {52365383, 39342846}, {45742346, 34366870}, {35340593, 26551926}, {35340112, 26551570}, {55331116, 41571027}, {50378234, 37849844}, {47108115, 35392957}, {50112205, 37650001}, {35342007, 26552955}, {35345802, 26555838}, {35343421, 26554049}, {35344630, 26554957}, {56351609, 42337785}, {48984442, 36802673}, {47203632, 35464779}, {35344085, 26554541}, {51452340, 38656855}, {55648601, 41809557}, {35345877, 26555908}, {35341138, 26552334}, {39288354, 29517910}, {40818074, 30667209}, {53116798, 39907380}, {44282278, 33269884}, {35344195, 26554658}, {42175960, 31687424}, {41386791, 31094496}, {35345884, 26555916}, {35344085, 26554543}, {45592756, 34254483}, {54467292, 40922075}, {50176248, 37698151}, {51566541, 38742662}, {35340317, 26551668}, {35344863, 26555100}, {44378252, 33342021}, {52056457, 39110735}, {54290116, 40788901}, {55856444, 41965711}, {47619870, 35777509}, {36500085, 27423004}, {35341962, 26552955}, {35340202, 26551663}, {44233227, 33233063}, {35340841, 26552097}, {51121114, 38407981}, {35340233, 26551668}, {46379481, 34845535}, {35345684, 26555747}, {35342492, 26553384}, {35340702, 26552013}, {35342866, 26553637}, {36369362, 27324804}, {35343577, 26554181}, {42463051, 31903064}, {43369164, 32583840}, {37344603, 28057503}, {36168310, 27173735}, {35340770, 26552087}, {49044421, 36847746}, {35341800, 26552825}, {53133339, 39919806}, {35340698, 26552013}, {35340745, 26552058}, {35342280, 26553192}, {35343541, 26554145}, {46688459, 35077682}, {35340763, 26552079}, {35341801, 26552827}, {35345948, 26555958}, {45009657, 33816364}, {35342901, 26553656}, {35343160, 26553813}, {41663616, 31302432}, {35341236, 26552411}, {35344267, 26554671}, {35345803, 26555839}, {45897594, 34483477}, {35345850, 26555877}, {45865728, 34459601}, {50789930, 38159157}, {35343054, 26553804}, {35341390, 26552526}, {47191557, 35455688}, {51026964, 38337282}, {35345885, 26555920}, {42169108, 31682276}, {48563616, 36486501}, {38651709, 29039607}, {35340661, 26551970}, {35345327, 26555507}, {47953776, 36028315}, {35344578, 26554920}, {44375937, 33340259}, {35341342, 26552520}, {35342919, 26553671}, {46767358, 35137010}, {35342303, 26553211}, {35344683, 26555004}, {35345204, 26555388}, {41881817, 31466403}, {35340005, 26551481}, {57013221, 42834805}, {35342583, 26553384}, {35344022, 26554502}, {35343530, 26554140}, {35345876, 26555907}, {35340545, 26551884}, {35344091, 26554555}, {41393648, 31099658}, {35341411, 26552526}, {35345246, 26555429}, {56890075, 42742334}, {39730331, 29849994}, {41222828, 30971264}, {54061857, 40617459}, {37314429, 28034892}, {35343710, 26554242}, {35345842, 26555875}, {43873493, 32962788}, {38255447, 28741897}, {55054114, 41362903}, {35345226, 26555399}, {45519036, 34199066}, {48004666, 36066592}, {49397879, 37113306}, {35340786, 26552097}, {35344740, 26555057}, {47173262, 35441921}, {48831413, 36687701}, {53654678, 40311498}, {35342592, 26553384}, {35340140, 26551586}, {37819184, 28414111}, {35342917, 26553670}, {35343039, 26553788}, {54389564, 40863630}, {35345301, 26555475}, {43887652, 32973384}, {35344199, 26554666}, {46272852, 34765480}, {35343754, 26554242}, {38418711, 28864526}, {56252741, 42263468}, {35344829, 26555100}, {35345319, 26555500}, {54660746, 41067356}, {35341176, 26552362}, {36531534, 27446647}, {35341767, 26552803}, {35344619, 26554945}, {35444342, 26629824}, {55337906, 41576119}, {35343457, 26554077}, {35343610, 26554217}, {49467443, 37165558}, {35343404, 26554032}, {40548586, 30464765}, {35341773, 26552812}, {38262808, 28747378}, {35340828, 26552097}, {35341867, 26552897}, {36314525, 27283600}, {35340180, 26551643}, {35343136, 26553813}, {35343606, 26554216}, {35343515, 26554115}, {51400365, 38617798}, {35340770, 26552084}, {35343969, 26554457}, {45073166, 33864118}, {35343071, 26553813}, {35344187, 26554652}, {47169919, 35439457}, {35342871, 26553639}, {50990985, 38310242}, {50067028, 37616026}, {45565953, 34234377}, {51902285, 38994902}, {46908239, 35242793}, {35344694, 26555013}, {53230474, 39992821}, {35342613, 26553384}, {35340799, 26552097}, {48284966, 36277148}, {35344621, 26554947}, {35345715, 26555771}, {35340685, 26551999}, {35341712, 26552766}, {38205145, 28704066}, {47426743, 35632348}, {55161028, 41443274}, {37819046, 28413965}, {35345314, 26555491}, {52171202, 39196941}, {35344299, 26554671}, {50977845, 38300371}, {35344728, 26555046}, {55353379, 41587748}, {54483124, 40933907}, {37888893, 28466494}, {35345983, 26555958}, {52276214, 39275823}, {35342274, 26553188}, {45919887, 34500234}, {56364082, 42347113}, {44543279, 33466008}, {41610650, 31262706}, {35343029, 26553772}, {35342359, 26553250}, {35374741, 26577577}, {35342954, 26553709}, {35345689, 26555749}, {35340661, 26551973}, {35344845, 26555100}, {35465452, 26645687}, {55435637, 41649563}, {35345813, 26555856}, {35343153, 26553813}, {35342845, 26553620}, {35345812, 26555854}, {35342255, 26553169}, {56967714, 42800669}, {54104153, 40649240}, {36182613, 27184486}, {35345339, 26555522}, {43881403, 32968687}, {44176698, 33190598}, {35345734, 26555788}, {52782723, 39656369}, {35340264, 26551668}, {35345271, 26555446}, {37188660, 27940345}, {52077629, 39126678}, {42873685, 32211639}, {35344090, 26554552}, {41410146, 31112014}, {51206840, 38472432}, {36340274, 27302974}, {35340057, 26551520}, {35340101, 26551564}, {45677205, 34317899}, {42708141, 32087261}, {51675036, 38824181}, {35344584, 26554926}, {51962347, 39040066}, {35346033, 26555958}, {42068011, 31606326}, {52798149, 39667961}, {51129216, 38414135}, {35340607, 26551935}, {35344079, 26554539}, {35344694, 26555014}, {54042420, 40602837}, {55089654, 41389650}, {35343726, 26554242}, {48206770, 36218410}, {45770963, 34388406}, {53049438, 39856787}, {35343025, 26553766}, {38291929, 28769259}, {47968608, 36039458}, {35339980, 26551459}, {35345355, 26555529}, {35341207, 26552384}, {35343051, 26553797}, {35343177, 26553813}, {36098062, 27120965}, {39984940, 30041239}, {39597869, 29750476}, {35343690, 26554242}, {35344870, 26555100}, {35344327, 26554671}, {35343170, 26553813}, {51129135, 38414020}, {47635783, 35789436}, {38299988, 28775362}, {53626715, 40290504}, {45926773, 34505422}, {35345108, 26555312}, {52693741, 39589575}, {35340178, 26551633}, {41277077, 31012024}, {48647558, 36549581}, {35344162, 26554620}, {50456588, 37908779}, {35345316, 26555491}, {52268790, 39270245}, {36555538, 27464697}, {35340057, 26551524}, {48345595, 36322760}, {35344548, 26554892}, {35342287, 26553197}, {35340879, 26552097}, {35342946, 26553689}, {35345751, 26555798}, {43729611, 32854701}, {35344163, 26554621}, {35343491, 26554101}, {52476187, 39426119}, {35344316, 26554671}, {35342837, 26553607}, {54000162, 40571086}, {37470333, 28152032}, {35342004, 26552955}, {35340682, 26551998}, {35342824, 26553595}, {35341681, 26552737}, {35340544, 26551884}, {35342350, 26553243}, {35340834, 26552097}, {35343443, 26554068}, {35345242, 26555427}, {50156119, 37682967}, {35345804, 26555841}, {44377752, 33341657}, {35342450, 26553334}, {38177778, 28683535}, {35340162, 26551615}, {35344217, 26554671}, {35340224, 26551668}, {35345136, 26555337}, {35341321, 26552496}, {50111694, 37649657}, {35341121, 26552317}, {51919909, 39008126}, {56511401, 42457797}, {35344738, 26555055}, {35343027, 26553770}, {40531982, 30452255}, {35341784, 26552819}, {35340090, 26551543}, {35342945, 26553686}, {35340665, 26551981}, {35344839, 26555100}, {35341291, 26552461}, {51211363, 38475799}, {35344689, 26555009}, {55974728, 42054631}, {35340842, 26552097}, {44182361, 33194817}, {35344104, 26554572}, {35342509, 26553384}, {37035722, 27825452}, {55302608, 41549622}, {50373140, 37846031}, {37297162, 28021864}, {35341449, 26552526}, {39806170, 29906912}, {35345923, 26555958}, {35341752, 26552795}, {35340080, 26551536}, {50262407, 37762866}, {56284097, 42287000}, {35343716, 26554242}, {35341199, 26552381}, {35345952, 26555958}, {35340751, 26552069}, {49247682, 37000461}, {35344008, 26554495}, {35343732, 26554242}, {35341804, 26552834}, {35340613, 26551937}, {35340196, 26551651}, {35342831, 26553603}, {35344240, 26554671}, {35341877, 26552903}, {35343605, 26554211}, {35341153, 26552350}, {35344852, 26555100}, {56074001, 42129164}, {54995997, 41319277}, {41753423, 31369969}, {51109747, 38399472}, {48809282, 36671126}, {38990843, 29294375}, {35748621, 26858418}, {47456448, 35654676}, {54789831, 41164352}, {35341195, 26552373}, {35345157, 26555355}, {35342353, 26553243}, {44289745, 33275522}, {35341841, 26552871}, {44972007, 33788072}, {35345449, 26555529}, {50031758, 37589598}, {44669956, 33561138}, {35344150, 26554611}, {35345422, 26555529}, {35345179, 26555369}, {35339998, 26551479}, {35343087, 26553813}, {35345420, 26555529}, {35345890, 26555930}, {35343052, 26553798}, {55772595, 41902767}, {53508472, 40201637}, {35863378, 26944642}, {35345118, 26555319}, {49071253, 36867907}, {35342977, 26553723}, {35343021, 26553763}, {35341887, 26552916}, {35342829, 26553602}, {35341236, 26552412}, {35340800, 26552097}, {46331634, 34809618}, {35342443, 26553328}, {43808887, 32914204}, {35345415, 26555529}, {55306594, 41552615}, {35342317, 26553219}, {35345298, 26555473}, {56327563, 42319696}, {35340318, 26551668}, {41254796, 30995281}, {35345192, 26555375}, {36188882, 27189190}, {36830645, 27671366}, {37088175, 27864848}, {56835195, 42701088}, {35340791, 26552097}, {35343416, 26554047}, {49804925, 37419110}, {42621164, 32021857}, {48472270, 36417882}, {35343544, 26554148}, {35345887, 26555921}, {41667153, 31305140}, {40412144, 30362247}, {35344659, 26554976}, {35345895, 26555934}, {39745156, 29861111}, {48062910, 36110351}, {40572474, 30482659}, {47603247, 35764993}, {35340690, 26552003}, {38169102, 28676957}, {35342038, 26552955}, {47710641, 35845712}, {55662998, 41820416}, {57038486, 42853846}, {39943308, 30009995}, {55772521, 41902661}, {35343511, 26554111}, {35344551, 26554896}, {35341285, 26552448}, {51925096, 39012038}, {41810344, 31412677}, {53913277, 40505768}, {35341125, 26552322}, {56184159, 42211911}, {35342846, 26553620}, {51765773, 38892322}, {50272648, 37770532}, {35344237, 26554671}, {39328831, 29548281}, {35345180, 26555369}, {35345924, 26555958}, {47677968, 35821096}, {35345193, 26555379}, {35345733, 26555787}, {35341387, 26552526}, {35340895, 26552097}, {35340104, 26551566}, {39270015, 29504092}, {44874425, 33714789}, {42807914, 32162158}, {35343560, 26554159}, {46591316, 35004685}, {45911321, 34493798}, {35341965, 26552955}, {35345177, 26555367}, {44264594, 33256610}, {47857325, 35955878}, {35886888, 26962333}, {45524319, 34203089}, {35344144, 26554599}, {35343608, 26554217}, {35342386, 26553280}, {38556788, 28968239}, {51237162, 38495232}, {43577726, 32740590}, {45312376, 34043816}, {35341989, 26552955}, {35341927, 26552955}, {35344550, 26554894}, {35343456, 26554075}, {39609207, 29758941}, {56074030, 42129202}, {35341933, 26552955}, {51568866, 38744435}, {35343970, 26554458}, {49421857, 37131322}, {47340016, 35567225}, {35344115, 26554577}, {35342945, 26553687}, {35343481, 26554094}, {35341788, 26552822}, {35341131, 26552329}, {42994169, 32302162}, {35346013, 26555958}, {35340098, 26551561}, {35345116, 26555319}, {53938440, 40524680}, {35343471, 26554084}, {35344769, 26555095}, {44672858, 33563344}, {35342452, 26553335}, {35344158, 26554617}, {35343552, 26554155}, {35344864, 26555100}, {35340202, 26551665}, {35342258, 26553171}, {48686902, 36579128}, {51857209, 38961061}, {56550229, 42486972}, {35344242, 26554671}, {45315822, 34046425}, {39142707, 29408455}, {53423395, 40137718}, {35342391, 26553285}, {35344066, 26554530}, {35341168, 26552357}, {35669901, 26799299}, {36273963, 27253115}, {50595373, 38013042}, {45767946, 34386077}, {35342889, 26553649}, {48834881, 36690330}, {43260217, 32502012}, {35345940, 26555958}, {53415460, 40131793}, {35344298, 26554671}, {35344051, 26554520}, {35340007, 26551482}, {46385246, 34849861}, {35345176, 26555367}, {35345118, 26555320}, {35343614, 26554221}, {35341736, 26552785}, {35344667, 26554994}, {35341275, 26552443}, {38334744, 28801421}, {46366398, 34835699}, {38619107, 29015070}, {56633563, 42549573}, {35341112, 26552310}, {35341371, 26552526}, {35340030, 26551502}, {35343171, 26553813}, {35342424, 26553302}, {52321978, 39310235}, {35345908, 26555944}, {35343130, 26553813}, {35343628, 26554238}, {55866129, 41972975}, {51187968, 38458231}, {35343401, 26554031}, {35344062, 26554530}, {36252375, 27236960}, {55327092, 41567988}, {35340716, 26552019}, {54762945, 41144138}, {35340659, 26551969}, {35345853, 26555878}, {56643938, 42557417}, {43180842, 32442378}, {52117555, 39156639}, {35343496, 26554101}, {35341338, 26552509}, {35344610, 26554941}, {35340108, 26551569}, {39609262, 29759015}, {35341134, 26552333}, {40321230, 30293885}, {35344261, 26554671}, {49808437, 37421780}, {48539657, 36468510}, {35343008, 26553754}, {35342478, 26553366}, {55106128, 41401976}, {54254837, 40762430}, {35344007, 26554491}, {35340092, 26551548}, {35341794, 26552823}, {35941198, 27003170}, {36482502, 27409860}, {35345395, 26555529}, {44816077, 33670924}, {35341249, 26552425}, {41160031, 30924093}, {35342957, 26553709}, {35342266, 26553177}, {35340545, 26551885}, {35343035, 26553788}, {48397395, 36361620}, {35344116, 26554579}, {35340070, 26551527}, {35343109, 26553813}, {35345236, 26555421}, {53619854, 40285335}, {46419595, 34875726}, {42549842, 31968272}, {35340107, 26551567}, {35343026, 26553769}, {40819838, 30668551}, {55684628, 41836652}, {37059051, 27842968}, {35343966, 26554454}, {45964950, 34534085}, {35341119, 26552316}, {35340210, 26551668}, {35344139, 26554591}, {43500105, 32682274}, {35340601, 26551930}, {35344691, 26555011}, {35345175, 26555367}, {35345418, 26555529}, {35344865, 26555100}, {40643357, 30535958}, {35345790, 26555826}, {35341943, 26552955}, {35343578, 26554181}, {35345178, 26555368}, {35342829, 26553601}, {35347211, 26556832}, {35343596, 26554194}, {54765755, 41146256}, {35344095, 26554563}, {36624570, 27516533}, {35345804, 26555843}, {35342829, 26553600}, {41431342, 31127987}, {46095829, 34632467}, {35345113, 26555316}, {35345166, 26555361}, {35340272, 26551668}, {37358273, 28067770}, {35341834, 26552865}, {42599548, 32005629}, {35997092, 27045130}, {35340642, 26551961}, {35345836, 26555871}, {46211171, 34719126}, {35341743, 26552788}, {46200260, 34710891}, {35340771, 26552088}, {55729753, 41870577}, {35345462, 26555529}, {46916398, 35248981}, {36934670, 27749544}, {35344707, 26555018}, {35343618, 26554224}, {42452268, 31895001}, {35345749, 26555796}, {46486919, 34926263}, {35343435, 26554059}, {49040415, 36844738}, {35341801, 26552828}, {35345841, 26555874}, {35343043, 26553792}, {43785607, 32896775}, {35345899, 26555938}, {39630858, 29775193}, {35340117, 26551573}, {35999878, 27047189}, {35344579, 26554925}, {46767230, 35136847}, {44247497, 33243782}, {37537126, 28202211}, {35346038, 26555958}, {49617188, 37278113}, {52783416, 39656955}, {43985417, 33046890}, {35345830, 26555866}, {43512496, 32691526}, {35341840, 26552870}, {35344213, 26554671}, {52216304, 39230819}, {45702992, 34337334}, {35342912, 26553666}, {45789700, 34402430}, {46593108, 35006041}, {35343029, 26553771}, {35343625, 26554229}, {35344570, 26554913}, {40717627, 30591760}, {37799218, 28399126}, {35345780, 26555819}, {43974576, 33038754}, {35340598, 26551929}, {56803761, 42677461}, {35342361, 26553251}, {35345884, 26555917}, {36878079, 27706993}, {36723425, 27590845}, {35344582, 26554925}, {35344123, 26554585}, {42232385, 31729772}, {35341897, 26552931}, {35344883, 26555100}, {35341212, 26552386}, {35342002, 26552955}, {35340644, 26551963}, {53115165, 39906203}, {44294793, 33279272}, {51794295, 38913748}, {35344660, 26554981}, {35340325, 26551668}, {35341901, 26552934}, {53444591, 40153685}, {39274542, 29507492}, {39257989, 29495052}, {35344800, 26555100}, {35345684, 26555745}, {35339997, 26551476}, {48074359, 36118976}, {37802505, 28401531}, {56011211, 42082007}, {53917942, 40509313}, {54417505, 40884605}, {35340662, 26551976}, {42874310, 32212101}, {35343000, 26553735}, {55809562, 41930475}, {35344566, 26554911}, {35345250, 26555430}, {51831467, 38941692}, {36435630, 27374615}, {35345232, 26555410}, {44511765, 33442285}, {35344722, 26555044}, {35343047, 26553795}, {35340692, 26552006}, {47289138, 35528967}, {35342378, 26553272}, {37347525, 28059739}, {35341249, 26552427}, {35344129, 26554588}, {35344177, 26554646}, {56276088, 42280979}, {50542798, 37973548}, {39003870, 29304137}, {56323516, 42316629}, {35344874, 26555100}, {47527327, 35707971}, {47524961, 35706143}, {35344199, 26554667}, {35343981, 26554467}, {44036867, 33085546}, {35343720, 26554242}, {35340888, 26552097}, {48849758, 36701522}, {35344296, 26554671}, {35340737, 26552047}, {35345947, 26555958}, {35345121, 26555323}, {39562432, 29723828}, {47371903, 35591145}, {35341984, 26552955}, {35345978, 26555958}, {44640938, 33539400}, {42909511, 32238497}, {55971200, 42051920}, {35345851, 26555877}, {47699749, 35837501}, {35342950, 26553702}, {35342486, 26553379}, {35343049, 26553796}, {35343626, 26554236}, {35345854, 26555880}, {35345377, 26555529}, {35344702, 26555017}, {35751028, 26860284}, {35340095, 26551558}, {35341267, 26552439}, {37573544, 28229505}, {40913406, 30738821}, {35341463, 26552526}, {35340246, 26551668}, {35340698, 26552012}, {40142548, 30159667}, {35344095, 26554564}, {53068322, 39871008}, {56781539, 42660797}, {35345260, 26555439}, {41648289, 31290958}, {35345769, 26555814}, {53043719, 39852486}, {35340060, 26551525}, {49610934, 37273427}, {38991424, 29294811}, {35345775, 26555817}, {35340146, 26551593}, {38693362, 29070873}, {40787805, 30644471}, {39662232, 29798772}, {35340039, 26551506}, {41721878, 31346208}, {38739636, 29105655}, {35341191, 26552369}, {41084779, 30867605}, {53649594, 40307718}, {48002318, 36064789}, {47004843, 35315410}, {35342254, 26553169}, {35343520, 26554128}, {35343514, 26554113}, {35341832, 26552861}, {35340545, 26551886}, {35343437, 26554066}, {42850885, 32194506}, {35345820, 26555859}, {35340725, 26552041}, {35343614, 26554220}, {56402422, 42375958}, {55093629, 41392623}, {52270020, 39271200}, {54248026, 40757339}, {48390653, 36356577}, {44872206, 33713157}, {35345189, 26555374}, {38026509, 28569889}, {35344054, 26554523}, {46070577, 34613449}, {37115663, 27885513}, {47995536, 36059735}, {35341888, 26552917}, {35344024, 26554502}, {35344717, 26555036}, {35340742, 26552053}, {35340018, 26551495}, {42521400, 31946966}, {38840593, 29181456}, {53426786, 40140261}, {45520221, 34199974}, {56183072, 42211116}, {35340663, 26551976}, {43164241, 32429881}, {38074874, 28606164}, {35340889, 26552097}, {35342948, 26553695}, {35342418, 26553301}, {45789832, 34402574}, {51954850, 39034421}, {35345700, 26555762}, {35343433, 26554056}, {50282410, 37777914}};
        int x = 35339967;
        int y = 26551450;
        Assert.assertTrue(robot(command,obstacles,x,y));
    }

    public boolean robot2(String command, int[][] obstacles, int x, int y) {
        int positionX = 0, positionY = 0;
        Map<String,Integer> map = new HashMap<>();
        int rows = obstacles.length;
        for (int i = 0; i < rows; i++){
            String key = obstacles[i][0] +""+ obstacles[i][1];
            map.put(key,1);
        }
        while (true){
            for (Character character : command.toCharArray()){
                if ('U' == character.charValue()){
                    positionY++;
                }else {
                    positionX++;
                }
                if (map.containsKey(positionX+""+positionY)){
                    return false;
                }
                if (positionX > x || positionY > y){
                    return false;
                }
                if (positionX==x && positionY==y){
                    return true;
                }
            }
        }
    }


    //获取执行指令中UR的个数
    public int[] getDirectionCount(String command, int length) {
        int[] count = new int[]{0, 0};
        char[] charArr = command.toCharArray();
        for (int i = 0; i < length; i++) {
            if (charArr[i] == 'U') {
                count[0]++;
            } else {
                count[1]++;
            }
        }
        return count;
    }

//    public boolean getCount(int x, int y, String command) {
//        for(;command.length()<x+y;){
//            command +=command;
//        }
//        int[] yxStep = getDirectionCount(command, x+y);
//        return y == yxStep[0] && x == yxStep[1];
//    }

    //方法如上，但是提交后，会涉及到内存溢出，原因是有的x+y非常大，导致command拼接的次数相当巨大，所以优化也在command拼接那里优化
    //优化后通过command的长度来获取移动指令中UR的次数
    public boolean getCount(int x, int y, String command) {
        int times = (x + y) / command.length();
        int extra = (x + y) % command.length();
        int[] yxStep = getDirectionCount(command, command.length());
        int[] yxStepEx = getDirectionCount(command, extra);
        yxStep[0] = yxStep[0] * times + yxStepEx[0];
        yxStep[1] = yxStep[1] * times + yxStepEx[1];
        return y == yxStep[0] && x == yxStep[1];
    }

    public boolean robot(String command, int[][] obstacles, int x, int y) {
        for (int[] point : obstacles) {
            int pX = point[0];
            int pY = point[1];
            if (pX + pY > x + y) { //障碍点不在起终点的范围内
                continue;
            }
            if (getCount(pX, pY, command)) { //经过障碍点
                return false;
            }
        }
        return getCount(x, y, command);//经过终点

    }
}
