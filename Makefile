swc:
	flink run -j build/libs/FlinkPOC-1.0.jar -c edu.iu.dsc.spidal.flink.examples.streaming.WindowWordCount

bwc:
	flink run -j build/libs/FlinkPOC-1.0.jar -c edu.iu.dsc.spidal.flink.examples.batch.WordCount --input file:///home/vibhatha/github/FlinkStreaming/data/input/input.txt --output file:///home/vibhatha/github/FlinkStreaming/data/output/output.txt

testinit:
	gradle test --tests edu.iu.dsc.spida.flink.test.SimpleTest.initialTest --console auto

streamio:
	flink run -j build/libs/FlinkPOC-1.0.jar -c edu.iu.dsc.spidal.flink.examples.batch.BatchWordCountTask --input file:///home/vibhatha/github/FlinkStreaming/data/input/input.txt --output file:///home/vibhatha/github/FlinkStreaming/data/output/output.txt

libsvmds:
	flink run -j build/libs/FlinkPOC-1.0.jar -c edu.iu.dsc.spidal.flink.examples.dataset.LibSVMReadTask --input file:///home/vibhatha/github/FlinkStreaming/data/input/heart.libsvm --output file:///home/vibhatha/github/FlinkStreaming/data/output/heart-libsvm.csv --features 13

test:
	gradle test
