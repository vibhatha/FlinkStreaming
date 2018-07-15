swc:
	flink run -j build/libs/FlinkPOC-1.0.jar -c edu.iu.dsc.spidal.flink.streaming.WindowWordCount

bwc:
	flink run -j build/libs/FlinkPOC-1.0.jar -c edu.iu.dsc.spidal.flink.batch.WordCount --input file:///home/vibhatha/github/dsc-spidal-forks/FlinkPOCdata/input/input.txt --output file:///home/vibhatha/github/dsc-spidal-forks/FlinkPOCdata/output/output.txt

