package it.reply.labcamp.coherence.extractor;

import com.tangosol.util.extractor.MultiExtractor;
import com.tangosol.util.extractor.PofExtractor;

import it.reply.labcamp.coherence.model.value.Category;
import it.reply.labcamp.coherence.model.value.PriceRange;
import it.reply.labcamp.coherence.model.value.ProductValue;
/*
 * https://docs.oracle.com/middleware/12213/coherence/java-reference/com/tangosol/util/extractor/MultiExtractor.html
 */
public class ExtractorRegister {

	public static final PofExtractor<ProductValue, Category> CATEGORY_EXTRACTOR = null;
	
	public static final PofExtractor<ProductValue, PriceRange> PRICE_RANGE_EXTRACTOR = null;
	
	public static final MultiExtractor CATEGORY_AND_PRICE_RANGE_EXTRACTOR = null;
}
