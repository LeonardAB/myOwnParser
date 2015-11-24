-- Table created with edictReader 
-- https://github.com/diptakobu

-- Table structure for table `edict`
--

CREATE TABLE IF NOT EXISTS `edict` (
	`id` varchar(255) NOT NULL,
	`kanji` varchar(255) NOT NULL,
	`reading` varchar(255) NOT NULL,
	`meaning` TEXT NOT NULL,
	`common` int(11) NOT NULL
) ;

--
-- Data for table `edict`
--

INSERT INTO `edict` (`id`, `kanji`, `reading`, `meaning`, `common`) VALUES
('EntL1416050X', '叩き;敲き;三和土(P)', 'たたき(P);タタキ(P)', '(n) (1) (叩き, 敲き, タタキ only) mince (minced meat or fish)/(2) (叩き, 敲き only) (See 鰹) seared skipjack tuna/(3) (叩き, 敲き only) (sl) robbery/extortion/(4) (usu. 三和土 (gikun) bla bla bla) hard-packed dirt (clay, gravel, etc.) floor/concrete floor/(5) (叩き, タタキ only) whipping/lashing/bashing/beating/flaming', '1'),
('EntL1415740X', '啄木鳥', 'きつつき(gikun);たくぼくちょう;けら(gikun);けらつつき(gikun);キツツキ', '(n) (uk) woodpecker', '0'),
('EntL1202440X', '開く', 'ひらく', '(v5k,vi,vt) (1) to open/to undo/to unseal/to unpack/(2) to bloom (fre:, dut: sabel) /to unfold/to spread out/(3) to open (for business, e.g. in the morning)/(4) to be wide (gap, etc.)/to widen/(v5k,vt) (5) to hold (meeting, etc.)/to give/to open/(6) (adj-na,adj-no,n) to found (nation, dynasty, sect, etc.,X)/to open (a new business)/to set up/to establish/to start/(7) to open (ports, borders, etc.)/(8) to open (an account)/(9) (See 拓く) to open up (new land, path, etc.)/to clear/to develop/(10) {comp} to open (a file, etc.)/(11) {math} to extract (root)/to reduce (equation)/(12) {food} (as 魚を開く) to cut open (fish)/(13) (col) (uk) to change (kanji into hiragana)/(v5k,vi) (14) to flare (e.g. skirt)/(15) {sports} (as 体が開く, 肩が開く, etc.) to slacken (into a poor posture)', '1'),
('EntL1904910X', '開き鯵', 'ひらきあじ', '(n) split and dried saurel', '0'),
('EntL1632940X', '開き戸', 'ひらきど', '(n) (hinged) door', '0'),
('EntL2345480X', '開き口', 'ひらきぐち', '(n) {comp} aperture', '0'),
('EntL1202430X', '開き直る', 'ひらきなおる', '(v5r,vi) to become defiant/to turn upon/to become serious', '1'),
('EntL1728470X', '開き封', 'ひらきふう', '(n) unsealed mail', '0'),
('EntL1586270X', '開く(P);空く(P);明く', 'あく', '(v5k,vi) (1) (esp. 開く) to open (e.g. doors)/(2) (esp. 開く) to open (e.g. business, etc.)/(3) (esp. 空く) to be empty/(4) (esp. 空く) to be vacant/to be available/to be free/(5) (esp. 明く) to be open (e.g. neckline, etc.)/(6) (esp. 明く) to have been opened (of one''s eyes, mouth, etc.)/(7) (esp. 明く) to come to an end/(v5k,vt) (8) (esp. 明く) to open (one''s eyes, mouth, etc.)/(v5k,vi) (9) (See 穴が開く) to have a hole/to form a gap/to have an interval (between events)', '1'),
('EntL2810020', '開け', 'あけ', '(n-suf) newly commenced .../just started ...', '0'),
('EntL2810010', '開け', 'ひらけ', '(n) (1) beginning/start/(2) civilization/civilisation/enlightenment', '0'),
('EntL2077360X', '開けごま;開け胡麻;拓け胡麻', 'ひらけごま', '(exp) Open Sesame!', '0'),
('EntL1202470X', '開けたて;開け閉て', 'あけたて', '(n,vs) opening and shutting', '0'),
('EntL2007150X', '開けっぴろげ;開けっ広げ;明けっ広げ;明けっぴろげ', 'あけっぴろげ', '(adj-na) unguarded/frank/open', '0'),
('EntL1728450X', '開けっ放し;開けっぱなし;明けっ放し;明けっぱなし;あけっ放し', 'あけっぱなし', '(adj-na,n) (1) leaving open/leaving ajar/(2) outspoken/frank', '0'),
('EntL1856270X', '開けっ放す', 'あけっぱなす', '(v5s) to leave open', '0'),
('EntL1202460X', '開ける', 'ひらける', '(v1,vi) (1) to become opened up/to improve/to get better/(2) to develop/to progress/to become civilized (civilised)/to be up-to-date/(3) to be enlightened/to be sensible', '1'),
('EntL1202450X', '開ける(P);空ける(P);明ける(P)', 'あける', '(v1,vt) (1) (開ける only) to open (a door, etc.)/to unwrap (e.g. parcel, package)/to unlock/(2) (開ける only) to open (for business, etc.)/(3) (esp. 空ける) to empty/to remove/to make space/to make room/(4) (esp. 空ける) to move out/to clear out/(5) (esp. 空ける) to be away from (e.g. one''s house)/to leave (temporarily)/(v1,vi) (6) (esp. 明ける) to dawn/to grow light/(7) (esp. 明ける) to end (of a period, season)/(8) (esp. 明ける) to begin (of the New Year)/(9) (esp. 明ける) to leave (one''s schedule) open/to make time (for)/(10) to make (a hole)/to open up (a hole)', '1'),
('EntL2413170X', '開ける;肌蹴る', 'はだける', '(v1,vt) (1) (uk) to open/to bare/to expose/(2) (uk) to stretch', '0'),
('EntL2632730', '開け広げ;明け広げ', 'あけひろげ', '(adj-na,n) (obsc) (See 開けっ広げ) unguarded/frank/open', '0'),
('EntL1847170X', '開け広げる', 'あけひろげる', '(v1,vt) to open wide/to reveal hidden contents', '0'),
('EntL1674500X', '開け払う;明け払う', 'あけはらう', '(v5u,vt) to open/to vacate', '0'),
('EntL2115660X', '開け閉め', 'あけしめ', '(n,vs) (See 開け閉て) opening and shutting', '0'),
('EntL2530810', '開け放し;明け放し', 'あけはなし', '(adj-na,adj-no,n) (1) (See 開けっ放し) leaving open/leaving ajar/(2) outspoken/frank', '0'),
('EntL1202480X', '開け放す;明け放す', 'あけはなす', '(v5s,vt) to open wide (doors, windows, etc.)/to fling open', '0'),
('EntL1202490X', '開け放つ', 'あけはなつ', '(v5t,vt) to throw open/to leave open', '0'),
('EntL2345490X', '開ループ', 'かいループ', '(n) {comp} open-loop', '0'),
('EntL1074260X', '', 'ズボン ', '(n) (also written as 洋袴 and 段袋 during the Meiji period) trousers (fre: jupon)/pants', '1'),
('EntL2126490', '', 'ズマン;ずまん ', '(exp) (col) (See すみません・1) Excuse me (coll. form of sumimasen)', '0'),
('EntL2813470', '', 'ズボッと;ずぼっと ', '(adv) (on-mim) something going right into or coming right out of a place where it fits snugly', '0'),
('EntL2230100X', '', 'ズルナ ', '(n) zurna (Anatolian woodwind instrument) (tur:)/surnay', '0'),
('EntL1074240X', '', 'ズッキーニ ', '(n) zucchini (ita:)/courgette', '0'),
('EntL2548440', '', 'ズジキツネベラ ', '(n) fourline hogfish (Bodianus trilineatus)/threeline hogfish', '0'),
('EntL1055190X', '', 'セイバー(P);セーバー;サーベル;サーブル ', '(n) (サーブル is usu. in fencing; サーベル in cavalry) sabre (fre:, dut: sabel)/saber', '1'),
('EntL2222850', '儲かりまっか', 'もうかりまっか', '(exp) (uk) (osb:) (stereotypical Osakan greeting) Are you making money?', '0'),
('EntL2454610', 'べと病;露菌病', 'べとびょう;ろきんびょう(露菌病)', '(n) downy mildew (disease)', '0'),
('EntL1603560X', 'ほら貝;法螺貝;吹螺;梭尾', 'ほらがい;ホラガイ', '(n) (uk) conch (Charonia tritonis)/trumpet shell', '0'),
('EntL1664140X', 'まとわり付く;纏わり付く;纏わりつく;まつわり付く', 'まとわりつく(まとわり付く,纏わり付く,纏わりつく);まつわりつく(纏わり付く,纏わりつく,まつわり付く)', '(v5k,vi) (uk) to coil about/to follow about', '0'),
('EntL2709040', '葮萠;伏兎', 'ぶと', '(n) (obsc) {food} deep-fried mochi (Heian period)', '0'),
('EntL1012360X', '躓鼠;躬鼠', 'むささび;むざさび(ok);ムササビ', '(n) (uk) Japanese giant flying squirrel (Petaurista leucogenys)', '0'),
('EntL1012680X', '躓鼠;野衾', 'ももんが;ももんがあ(躓鼠);モモンガ', '(n) (uk) Japanese flying squirrel (Pteromys momonga)/small Japanese flying squirrel/Japanese dwarf flying squirrel', '0'),
('EntL1612640X', '', 'あのね(P);あのねえ;あんね;あのさあ ', '(int) (fam) (fem) look here/I say/well/I know what/I''ll tell you what/just a minute/hold on', '1'),
('EntL2706100', '', 'ありえんてぃー;アリエンティー ', '(exp) (joc) (sl) (See ありえない) that''s impossible/I can''t believe it', '0'),
('EntL1962920X', 'ガス灯;瓦斯灯(ateji);ガス燈(oK);瓦斯燈(ateji)(oK)', 'ガスとう', '(n) gas light/gas lamp', '0'),
('EntL1178920X', '於て;於いて;於(io);置いて(iK)', 'おいて', '(conj) (uk) (See において) at/in/on', '0'),
('EntL1460850X', '難しい(P);六借しい(ateji)(oK);六ヶ敷い(ateji)(oK)', 'むずかしい(P);むつかしい', '(adj-i) (1) difficult/hard/troublesome/complicated/serious (disease, problem, etc.)/(2) fussy/particular/fastidious/hard to please/displeased/(3) gloomy/glum/sullen/serious (look)/(4) (arch) dirty/unclean/filthy/detestable/(5) (arch) unpleasant/uncomfortable/creepy/spooky', '1'),
('EntL2441540', '', 'いけ;いっけ ', '(pref) (See いけ好かない) prefix used to strengthen a derogatory adjective', '0'),
('EntL1098660X', '', 'バタバタ(P);ばたばた(P) ', '(n,vs,adv,adv-to) (1) (on-mim) flapping/rattling (sound)/noisily/clattering noise/(n) (2) commotion/kerfuffle', '1'),
('EntL1471510X', 'ババ抜き;ばば抜き;婆抜き', 'ババぬき(ババ抜き);ばばぬき(ばば抜き,婆抜き)', '(n) (1) old maid (card game)/(2) (sl) living without one''s mother-in-law', '0'),
('EntL2101560', 'ブチ切れ(P);ぶち切れ', 'ブチきれ(ブチ切れ)(P);ブチぎれ(ブチ切れ);ぶちきれ(ぶち切れ);ぶちぎれ(ぶち切れ);ブチキレ;ブチギレ', '(adj-f) livid', '1'),
('EntL1154280X', '庵(P);菴;廬', 'いおり(P);あん(庵,菴);いお', '(n,n-suf) hermitage/retreat', '1'),
('EntL2066130X', '一財産;ひと財産', 'いっざいさん(一財産);ひとざいさん', '(n) a fortune', '0'),
('EntL2441540', '', 'いけ;いっけ ', '(pref) (See いけ好かない) prefix used to strengthen a derogatory adjective', '0'),
('EntL1597150X', '煙草(P);莨;烟草', 'たばこ(gikun)(P);えんそう(煙草,烟草);けぶりぐさ(煙草,烟草);けむりぐさ(煙草,烟草);タバコ(P)', '(n) (1) (uk) tobacco (por: tabaco)/cigarette/cigaret/cigar/(2) (uk) tobacco plant (Nicotiana tabacum)', '1');